/*
 *
 */
package ieco.internal.middleware.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The type Null check.
 *
 * @param <T> the type parameter
 */
public class NullCheck<T> {

	/**
	 * The root.
	 */
	private T root;

	/**
	 * Instantiates a new Null check.
	 *
	 * @param root the root
	 */
	public NullCheck(@Nullable T root) {
		this.root = root;
	}

	/**
	 * With null check.
	 *
	 * @param <C>    the type parameter
	 * @param getter the getter
	 * @return the null check
	 */
	@NotNull
	public <C> NullCheck<C> with(Function<T, C> getter) {
		return root != null ? new NullCheck<>(getter.apply(root)) : new NullCheck<>(null);
	}

	/**
	 * With empty null check.
	 *
	 * @param <C>    the type parameter
	 * @param getter the getter
	 * @return the null check
	 */
	@NotNull
	public <C> NullCheck<C> withEmpty(Function<T, C> getter) {
		return (root != null && !StringUtils.isEmpty(root)) ? new NullCheck<>(getter.apply(root))
				: new NullCheck<>(null);
	}

	/**
	 * All not null null check.
	 *
	 * @param <V>     the type parameter
	 * @param objects the objects
	 * @return the null check
	 */
	@SafeVarargs
	@NotNull
	public final <V> NullCheck<V> allNotNull(@SuppressWarnings("unchecked") @Nullable final V... objects) {
		if (objects != null) {
			for (final V val : objects) {
				if (val == null) {
					return new NullCheck<>(null);
				}
			}
			return new NullCheck<>(objects[0]);
		}
		return new NullCheck<>(null);
	}

	/**
	 * Is null boolean.
	 *
	 * @return the boolean
	 */
	@NotNull
	public boolean isNull() {
		return root == null;
	}

	/**
	 * Is not null boolean.
	 *
	 * @return the boolean
	 */
	@NotNull
	public boolean isNotNull() {
		return root != null;
	}

	/**
	 * Is not null or empty boolean.
	 *
	 * @return the boolean
	 */
	@NotNull
	public boolean isNotNullOrEmpty() {
		return (root != null && !"".equals(root));
	}

	/**
	 * <p>
	 * This method can sort of work as {@link java.util.Optional#orElse} If there is
	 * any data it will return else return the provided value
	 * </p>
	 *
	 * @param value the else value
	 * @return the t
	 * @since 0.0.8
	 */
	@NotNull
	public T orElse(@NotNull T value) {
		return this.root != null ? this.root : value;
	}

	/**
	 * <p>
	 * Get the data if it is there else null return
	 * </p>
	 *
	 * @return the t
	 * @since 0.0.8
	 */
	@Nullable
	public T get() {
		return this.root;
	}

	/**
	 * Or else get t.
	 *
	 * @param other the other
	 * @return the t
	 * @since 0.0.9
	 */
	public T orElseGet(Supplier<? extends T> other) {
		return this.root != null ? this.root : other.get();
	}

	/**
	 * Check passed value before is {@code null} or {@code ""} then it will consider
	 * passed data and set it
	 *
	 * @param other the other
	 * @return the t
	 * @since 0.0.9
	 */
	public T orElseGetWithEmptyCheck(Supplier<? extends T> other) {
		return (root != null && !"".equals(root)) ? this.root : other.get();
	}

	/**
	 * Or else throw t.
	 *
	 * @param <X>               the type parameter
	 * @param exceptionSupplier the exception supplier
	 * @return the t
	 * @throws X the x
	 * @since 0.0.9
	 */
	public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
		if (root != null) {
			return root;
		} else {
			throw exceptionSupplier.get();
		}
	}

	/**
	 * Equals ignore case boolean.
	 *
	 * @param otherString the other string
	 * @return the boolean
	 */
	public boolean equalsIgnoreCase(@NotNull String otherString) {
		return root != null && otherString.equalsIgnoreCase((String) root);
	}

	/**
	 * Start with boolean.
	 *
	 * @param otherString the other string
	 * @return the boolean
	 */
	public boolean startWith(@NotNull String otherString) {
		if (root != null) {
			String rootString = (String) this.root;
			return rootString.startsWith(otherString);
		}
		return false;
	}

	/**
	 * Equals ignore case boolean.
	 *
	 * @param otherType the other type
	 * @return the boolean
	 */
	public boolean deepEquals(@NotNull T otherType) {
		return Objects.deepEquals(otherType, root);
	}
}
