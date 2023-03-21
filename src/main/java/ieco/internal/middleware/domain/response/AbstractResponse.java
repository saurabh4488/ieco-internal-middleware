package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.feignclient.RedisCacheClient;
import ieco.internal.middleware.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Abstract response.
 */
public abstract class AbstractResponse {

    @Autowired
    private static RedisCacheClient redisCacheClient;

    @Autowired
    private static ObjectMapper objectMapper;

    /**
     * Response success response object.
     *
     * @param message  the message
     * @param codeEnum the code enum
     * @return the response object
     */
    public static ResponseObject responseSuccess(String message, ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        return responseObject;
    }

    public static ResponseObject responseSuccess(ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(null);
        return responseObject;
    }

    /**
     * Response success response object.
     *
     * @param <T>      the type parameter
     * @param message  the message
     * @param codeEnum the code enum
     * @param attrs    the attrs
     * @param mapKey   the map key
     * @return the response object
     */
    public static <T> ResponseObject responseSuccess(String message, ResponseCodeEnum codeEnum, T attrs,
                                                     String mapKey) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        finMap.put(mapKey, attrs);
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return responseObject;
    }

    public static <T> ResponseObject responseSuccess(ResponseCodeEnum codeEnum, T attrs,
                                                     String mapKey) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        finMap.put(mapKey, attrs);
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(codeEnum.getValue());
        responseObject.setAttrs(finMap);
        return responseObject;
    }

    public static ResponseObject responseError(String message, ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(message);
        return responseObject;
    }

    public static ResponseObject responseError(ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(null);
        return responseObject;
    }

    public static <T> ResponseObject responseError(String message, ResponseCodeEnum codeEnum, T attrs, String mapKey) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        finMap.put(mapKey, attrs);
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return responseObject;
    }

    public static ResponseEntity<ResponseObject> responseEntitySuccess(String message, ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityError(String message, ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(message);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityBadRequestError(String message) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        responseObject.setStatus(HttpStatus.BAD_REQUEST.toString());
        responseObject.setMessage(message);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseObject> responseEntitySuccess(String message, ResponseCodeEnum codeEnum,
                                                                           T attrs, String mapKey) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        finMap.put(mapKey, attrs);
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseObject> responseEntityError(String message, ResponseCodeEnum codeEnum,
                                                                         T attrs, String mapKey) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        finMap.put(mapKey, attrs);
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseObject> responseEntityMultipleDataSuccess(String message,
                                                                                       ResponseCodeEnum codeEnum, List<T> attrs, List<String> mapKeyList) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        if (attrs.size() == mapKeyList.size()) {
            for (int i = 0; i < attrs.size(); i++) {
                finMap.put(mapKeyList.get(i), attrs.get(i));
            }
        }
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseObject> responseEntityCookieSuccess(String message,
                                                                                 ResponseCodeEnum codeEnum, T attrs, HttpHeaders headers, String mapKey) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        finMap.put(mapKey, attrs);
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return ResponseEntity.ok().headers(headers).body(responseObject);
    }

    /*public static <T> ResponseEntity<ResponseObject> responseEntityMultipleDataCookieSuccess(String message,
                                                                                             ResponseCodeEnum codeEnum, List<T> attrs, HttpHeaders headers, List<String> mapKeyList) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        if (attrs.size() == mapKeyList.size()) {
            for (int i = 0; i < attrs.size(); i++) {
                finMap.put(mapKeyList.get(i), attrs.get(i));
            }
        }
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return ResponseEntity.ok().headers(headers).body(responseObject);
    }*/

    public static <T> ResponseObject responseEntityMultipleDataCookieSuccess(String message, ResponseCodeEnum codeEnum, List<T> attrs, HttpHeaders headers, List<String> mapKeyList) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        if (attrs.size() == mapKeyList.size()) {
            for (int i = 0; i < attrs.size(); i++) {
                finMap.put(mapKeyList.get(i), attrs.get(i));
            }
        }
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return responseObject;
    }

    public static <T> ResponseObject responseMultipleDataSuccess(String message, ResponseCodeEnum codeEnum,
                                                                 List<T> attrs, List<String> mapKeyList) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        if (attrs.size() == mapKeyList.size()) {
            for (int i = 0; i < attrs.size(); i++) {
                finMap.put(mapKeyList.get(i), attrs.get(i));
            }
        }
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return responseObject;
    }

    public static ResponseObject responseSuccessIdeaLake(String message, ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(AppConstant.SUCCESS);
        responseObject.setMessage(message);
        return responseObject;
    }

    public static ResponseObject responseErrorIdeaLake(String message, ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(AppConstant.FAILURE);
        responseObject.setMessage(message);
        return responseObject;
    }

    public static <T> ResponseEntity<ResponseObject> responseEntityMultipleDataError(String message,
                                                                                     ResponseCodeEnum codeEnum, List<T> attrs, List<String> mapKeyList) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        if (attrs.size() == mapKeyList.size()) {
            for (int i = 0; i < attrs.size(); i++) {
                finMap.put(mapKeyList.get(i), attrs.get(i));
            }
        }
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static <T> ResponseObject responseMultipleDataError(String message, ResponseCodeEnum codeEnum, List<T> attrs,
                                                               List<String> mapKeyList) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        if (attrs.size() == mapKeyList.size()) {
            for (int i = 0; i < attrs.size(); i++) {
                finMap.put(mapKeyList.get(i), attrs.get(i));
            }
        }
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        return responseObject;
    }


    public static String getMessagefromMasterV1(ResponseCodeEnum codeEnum) {
        try {
            if (null != redisCacheClient) {
                Object codeEnumVal = redisCacheClient.getObjectFromCache("ERROR_MASTER");
                String codeEnumJson = objectMapper.writeValueAsString(codeEnumVal);
                return objectMapper.readValue(codeEnumJson, String.class);
            }
        } catch (IOException e) {
            //log.warn("Json parsing failed");
        }
        return "Message not available in DB";
    }


    public static ResponseEntity<ResponseObject> responseEntitySuccess(ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(getMessagefromMasterV1(codeEnum));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityError(ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(getMessagefromMasterV1(codeEnum));
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityError(String message) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setMessage(message);
        return new ResponseEntity<>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ResponseObject> responseEntityServerError(String message, ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        responseObject.setMessage(message);
        return new ResponseEntity<>(responseObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<ResponseObject> responseEntitySuccess(String message, T attrs, String mapKey) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        finMap.put(mapKey, attrs);
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        responseObject.setStatus(HttpStatus.OK.toString());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseObject> responseEntitySuccess(ResponseCodeEnum responseCodeEnum, T attrs, String mapKey) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        finMap.put(mapKey, attrs);
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setMessage("success");
        responseObject.setResponseCode(responseCodeEnum.getValue());
        responseObject.setAttrs(finMap);
        responseObject.setStatus(HttpStatus.OK.toString());
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityUnauthorized(String message) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setStatus(HttpStatus.UNAUTHORIZED.toString());
        responseObject.setMessage(message);
        return new ResponseEntity<>(responseObject, HttpStatus.UNAUTHORIZED);
    }

    public static ResponseObject responseErrorCB(String message, ResponseCodeEnum codeEnum) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.SERVICE_UNAVAILABLE.toString());
        responseObject.setMessage(message);
        return responseObject;
    }

    public static <T> ResponseObject responseEntityMultipleDataCookieFailure(String message,
                                                                             ResponseCodeEnum codeEnum, List<T> attrs, HttpHeaders headers, List<String> mapKeyList) {
        ResponseObject responseObject = new ResponseObject();
        Map<String, Object> finMap = new HashMap<>();
        if (attrs.size() == mapKeyList.size()) {
            for (int i = 0; i < attrs.size(); i++) {
                finMap.put(mapKeyList.get(i), attrs.get(i));
            }
        }
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setAttrs(finMap);
        //return ResponseEntity.ok().headers(headers).body(responseObject);
        return responseObject;
    }

    public static ResponseObject responseSuccess(String message, ResponseCodeEnum codeEnum, String id) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setTimeStamp(System.currentTimeMillis());
        responseObject.setResponseCode(codeEnum.getValue());
        responseObject.setStatus(HttpStatus.OK.toString());
        responseObject.setMessage(message);
        responseObject.setTicketId(id);
        return responseObject;
    }
}