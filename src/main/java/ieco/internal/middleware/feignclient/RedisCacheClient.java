package ieco.internal.middleware.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

//@FeignClient(name = "ieco-redis-cache-server", url = "http://localhost:8899/v1/cache/redis", configuration = FeignCacheConfig.class)
@FeignClient(name = "ieco-redis-cache-server/v1/cache/redis", configuration = FeignCacheConfig.class)
public interface RedisCacheClient {

    @PostMapping("/putObjectInCacheWithMapNameAndKeyWithEvictTime")
    <T> void putObjectInCacheWithEvictTime(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String masterKey, @RequestBody Object object, @RequestParam("timeout") long timeout, @RequestParam("timeUnit") TimeUnit timeUnit);

    @PostMapping("/putObjectInCacheWithMapNameAndKeyWithParamWithEvictTime")
    <T> void putObjectInCacheWithMapNameAndKeyWithParamWithEvictTime(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String masterKey, @RequestParam("data") T object, @RequestParam("timeout") long timeout, @RequestParam("timeUnit") TimeUnit timeUnit);

    @GetMapping("/getObjectFromCacheWithKey")
    Object getObjectFromCache(@RequestParam("masterKey") String key);

    @GetMapping("/getObjectFromCacheWithMapNameAndKey")
    Object getObjectFromCache(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String key);

}