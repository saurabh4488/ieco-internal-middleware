package ieco.internal.middleware.feignclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@RegisterRestClient
public interface RedisCacheClient {

    @PostMapping("/putObjectInCacheWithKey")
    void putObjectInCache(@RequestParam("masterKey") String masterKey, @RequestBody Object object);

    @PostMapping("/putObjectInCacheWithMapNameAndKey")
    void putObjectInCache(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String masterKey, @RequestBody Object object);

    @PostMapping("/putObjectInCacheWithKeyWithParam")
    <T> void putObjectInCacheWithKeyWithParam(@RequestParam("masterKey") String masterKey, @RequestParam("data") T object);

    @PostMapping("/putObjectInCacheWithMapNameAndKeyWithParam")
    <T> void putObjectInCacheWithMapNameAndKeyWithParam(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String masterKey, @RequestParam("data") T object);

    @PostMapping("/putObjectInCacheWithKeyWithEvictTime")
    void putObjectInCacheWithEvictTime(@RequestParam("masterKey") String masterKey, @RequestBody Object object, @RequestParam("timeout") long timeout, @RequestParam("timeUnit") TimeUnit timeUnit);

    @PostMapping("/putObjectInCacheWithMapNameAndKeyWithEvictTime")
    void putObjectInCacheWithEvictTime(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String masterKey, @RequestBody Object object, @RequestParam("timeout") long timeout, @RequestParam("timeUnit") TimeUnit timeUnit);

    @PostMapping("/putObjectInCacheWithKeyWithParamWithEvictTime")
    <T> void putObjectInCacheWithKeyWithParamWithEvictTime(@RequestParam("masterKey") String masterKey, @RequestParam("data") T object, @RequestParam("timeout") long timeout, @RequestParam("timeUnit") TimeUnit timeUnit);

    @PostMapping("/putObjectInCacheWithMapNameAndKeyWithParamWithEvictTime")
    <T> void putObjectInCacheWithMapNameAndKeyWithParamWithEvictTime(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String masterKey, @RequestParam("data") T object, @RequestParam("timeout") long timeout, @RequestParam("timeUnit") TimeUnit timeUnit);

    @GetMapping("/getObjectFromCacheWithKey")
    Object getObjectFromCache(@RequestParam("masterKey") String key);

    @GetMapping("/getObjectFromCacheWithMapNameAndKey")
    Object getObjectFromCache(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String key);

    @GetMapping("/readAllDataFromRedisCache")
    Object readAllDataFromRedisCache();

    @GetMapping("/readDataFromRedisCacheByName")
    Object readDataFromRedisCacheByName(@RequestParam("mapName") String cacheName);

    @DeleteMapping("/removeFromCacheWithKey")
    void removeFromCache(@RequestParam("masterKey") String key);

    @DeleteMapping("/removeFromCacheWithMapNameAndKey")
    void removeFromCache(@RequestParam("mapName") String mapName, @RequestParam("masterKey") String key);

}