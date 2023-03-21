package ieco.internal.middleware.util;

import com.itextpdf.text.BaseColor;

public class PciColors extends BaseColor{

public static final BaseColor AQUA = new BaseColor(255,0,0);
public static final BaseColor CURIOUS_BLUE  = new BaseColor(0, 0, 255);//indigo

public PciColors(float red, float green, float blue) {
super(red, green, blue);
}
}