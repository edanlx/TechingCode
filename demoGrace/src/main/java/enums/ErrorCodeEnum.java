package enums;

import com.example.demo.lesson.grace.front.Ifront;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/6/3 5:54 PM
 * @description
 */
public enum ErrorCodeEnum implements Ifront<ErrorCodeEnum> {
    SUCCESS("00000", "成功", "Success"),
    CLIENT_ERROR("A0001", "用户端错误", "Client error"),
    USER_REGISTRATION_ERROR("A0100", "用户注册错误", "User registration error"),
    USER_DOES_NOT_AGREE_TO_THE_PRIVACY_AGREEMENT("A0101", "用户未同意隐私协议", "User does not agree to the privacy agreement"),
    LIMITED_COUNTRY_OF_REGISTRATION("A0102", "注册国家或地区受限", "Limited country of registration"),
    USER_NAME_VERIFICATION_FAILED("A0110", "用户名校验失败", "User name verification failed"),
    USER_NAME_ALREADY_EXISTS("A0111", "用户名已存在", "User name already exists"),
    USER_NAME_CONTAINS_SENSITIVE_WORDS("A0112", "用户名包含敏感词", "User name contains sensitive words"),
    USER_NAME_CONTAINS_SPECIAL_CHARACTERS("A0113", "用户名包含特殊字符", "User name contains special characters"),
    PASSWORD_VERIFICATION_FAILED("A0120", "密码校验失败", "Password verification failed"),
    INSUFFICIENT_PASSWORD_LENGTH("A0121", "密码长度不够", "Insufficient password length"),
    INSUFFICIENT_PASSWORD_STRENGTH("A0122", "密码强度不够", "Insufficient password strength"),
    CHECK_CODE_INPUT_ERROR("A0130", "校验码输入错误", "Check code input error"),
    SMS_CHECK_CODE_INPUT_ERROR("A0131", "短信校验码输入错误", "SMS check code input error"),
    MAIL_CHECK_CODE_INPUT_ERROR("A0132", "邮件校验码输入错误", "Mail check code input error"),
    VOICE_CHECK_CODE_INPUT_ERROR("A0133", "语音校验码输入错误", "Voice check code input error"),
    USER_ID_IS_ABNORMAL("A0140", "用户证件异常", "User ID is abnormal"),
    USER_CERTIFICATE_TYPE_NOT_SELECTED("A0141", "用户证件类型未选择", "User certificate type not selected"),
    MAINLAND_ID_NUMBER_VERIFICATION_ILLEGAL("A0142", "大陆身份证编号校验非法", "Mainland ID number verification illegal"),
    PASSPORT_NUMBER_VERIFICATION_ILLEGAL("A0143", "护照编号校验非法", "Passport number verification illegal"),
    OFFICER_CERTIFICATE_NUMBER_VERIFICATION_ILLEGAL("A0144", "军官证编号校验非法", "Officer certificate number verification illegal"),
    USER_BASIC_INFORMATION_VERIFICATION_FAILED("A0150", "用户基本信息校验失败", "User basic information verification failed"),
    MOBILE_FORMAT_VERIFICATION_FAILED("A0151", "手机格式校验失败", "Mobile format verification failed"),
    ADDRESS_FORMAT_VERIFICATION_FAILED("A0152", "地址格式校验失败", "Address format verification failed"),
    MAILBOX_FORMAT_VERIFICATION_FAILED("A0153", "邮箱格式校验失败", "Mailbox format verification failed"),
    USER_LOGIN_EXCEPTION("A0200", "用户登陆异常", "User login exception"),
    USER_ACCOUNT_DOES_NOT_EXIST("A0201", "用户账户不存在", "User account does not exist"),
    USER_ACCOUNT_BLOCKED("A0202", "用户账户被冻结", "User account blocked"),
    USER_ACCOUNT_HAS_BEEN_VOIDED("A0203", "用户账户已作废", "User account has been voided"),
    USER_PASSWORD_ERROR("A0210", "用户密码错误", "User password error"),
    THE_NUMBER_OF_PASSWORD_INPUT_BY_THE_USER_EXCEEDS_THE_LIMIT("A0211", "用户输入密码次数超限", "The number of password input by the user exceeds the limit"),
    USER_IDENTITY_VERIFICATION_FAILED("A0220", "用户身份校验失败", "User identity verification failed"),
    USER_FINGERPRINT_RECOGNITION_FAILED("A0221", "用户指纹识别失败", "User fingerprint recognition failed"),
    USER_FACE_RECOGNITION_FAILED("A0222", "用户面容识别失败", "User face recognition failed"),
    THE_USER_IS_NOT_AUTHORIZED_TO_LOG_IN_BY_A_THIRD_PARTY("A0223", "用户未获得第三方登陆授权", "The user is not authorized to log in by a third party"),
    USER_LOGIN_EXPIRED("A0230", "用户登陆已过期", "User login expired"),
    USER_VERIFICATION_CODE_ERROR("A0240", "用户验证码错误", "User verification code error"),
    THE_NUMBER_OF_USER_VERIFICATION_CODE_ATTEMPTS_EXCEEDS_THE_LIMIT("A0241", "用户验证码尝试次数超限", "The number of user verification code attempts exceeds the limit"),
    ACCESS_PERMISSION_EXCEPTION("A0300", "访问权限异常", "Access permission exception"),
    UNAUTHORIZED_ACCESS("A0301", "访问未授权", "Unauthorized access"),
    AUTHORIZING("A0302", "正在授权中", "Authorizing"),
    USER_AUTHORIZATION_REQUEST_DENIED("A0303", "用户授权申请被拒绝", "User authorization request denied"),
    BLOCKED_DUE_TO_ACCESS_OBJECT_PRIVACY_SETTINGS("A0310", "因访问对象隐私设置被拦截", "Blocked due to access object privacy settings"),
    AUTHORIZATION_EXPIRED("A0311", "授权已过期", "Authorization expired"),
    NO_PERMISSION_TO_USE_API("A0312", "无权限使用 API", "No permission to use API"),
    USER_ACCESS_BLOCKED("A0320", "用户访问被拦截", "User access blocked"),
    BLACKLIST_USERS("A0321", "黑名单用户", "Blacklist users"),
    ACCOUNT_IS_BLOCKED("A0322", "账号被冻结", "Account is blocked"),
    ILLEGAL_IP_ADDRESS("A0323", "非法 IP 地址", "Illegal IP address"),
    RESTRICTED_GATEWAY_ACCESS("A0324", "网关访问受限", "Restricted gateway access"),
    REGION_BLACKLIST("A0325", "地域黑名单", "Region blacklist"),
    SERVICE_OVERDUE("A0330", "服务已欠费", "Service overdue"),
    USER_SIGNATURE_EXCEPTION("A0340", "用户签名异常", "User signature exception"),
    RSA_SIGNATURE_ERROR("A0341", "RSA 签名错误", "RSA signature error"),
    USER_REQUEST_PARAMETER_ERROR("A0400", "用户请求参数错误", "User request parameter error"),
    CONTAINS_ILLEGAL_AND_MALICIOUS_JUMP_LINKS("A0401", "包含非法恶意跳转链接", "Contains illegal and malicious jump links"),
    INVALID_USER_INPUT("A0402", "无效的用户输入", "Invalid user input"),
    REQUEST_REQUIRED_PARAMETER_IS_EMPTY("A0410", "请求必填参数为空", "Request required parameter is empty"),
    USER_ORDER_NUMBER_IS_EMPTY("A0411", "用户订单号为空", "User order number is empty"),
    ORDER_QUANTITY_IS_EMPTY("A0412", "订购数量为空", "Order quantity is empty"),
    MISSING_TIMESTAMP_PARAMETER("A0413", "缺少时间戳参数", "Missing timestamp parameter"),
    ILLEGAL_TIMESTAMP_PARAMETER("A0414", "非法的时间戳参数", "Illegal timestamp parameter"),
    REQUEST_PARAMETER_VALUE_OUT_OF_ALLOWED_RANGE("A0420", "请求参数值超出允许的范围", "Request parameter value out of allowed range"),
    PARAMETER_FORMAT_MISMATCH("A0421", "参数格式不匹配", "Parameter format mismatch"),
    ADDRESS_NOT_IN_SERVICE("A0422", "地址不在服务范围", "Address not in service"),
    TIME_OUT_OF_SERVICE("A0423", "时间不在服务范围", "Time out of service"),
    AMOUNT_EXCEEDS_LIMIT("A0424", "金额超出限制", "Amount exceeds limit"),
    QUANTITY_OUT_OF_LIMIT("A0425", "数量超出限制", "Quantity out of limit"),
    THE_TOTAL_NUMBER_OF_BATCH_PROCESSING_REQUESTS_EXCEEDS_THE_LIMIT("A0426", "请求批量处理总个数超出限制", "The total number of batch processing requests exceeds the limit"),
    FAILED_TO_REQUEST_JSON_PARSING("A0427", "请求 JSON 解析失败", "Failed to request JSON parsing"),
    ILLEGAL_USER_INPUT("A0430", "用户输入内容非法", "Illegal user input"),
    CONTAINS_PROHIBITED_SENSITIVE_WORDS("A0431", "包含违禁敏感词", "Contains prohibited sensitive words"),
    PICTURE_CONTAINS_PROHIBITED_INFORMATION("A0432", "图片包含违禁信息", "Picture contains prohibited information"),
    COPYRIGHT_INFRINGEMENT_OF_DOCUMENTS("A0433", "文件侵犯版权", "Copyright infringement of documents"),
    ABNORMAL_USER_OPERATION("A0440", "用户操作异常", "Abnormal user operation"),
    USER_PAYMENT_TIMEOUT("A0441", "用户支付超时", "User payment timeout"),
    CONFIRM_ORDER_TIMEOUT("A0442", "确认订单超时", "Confirm order timeout"),
    ORDER_CLOSED("A0443", "订单已关闭", "Order closed"),
    USER_REQUEST_SERVICE_EXCEPTION("A0500", "用户请求服务异常", "User request service exception"),
    THE_NUMBER_OF_REQUESTS_EXCEEDS_THE_LIMIT("A0501", "请求次数超出限制", "The number of requests exceeds the limit"),
    REQUEST_CONCURRENCY_EXCEEDED_LIMIT("A0502", "请求并发数超出限制", "Request concurrency exceeded limit"),
    PLEASE_WAIT_FOR_USER_ACTION("A0503", "用户操作请等待", "Please wait for user action"),
    WEBSOCKET_CONNECTION_EXCEPTION("A0504", "WebSocket 连接异常", "Websocket connection exception"),
    WEBSOCKET_DISCONNECTED("A0505", "WebSocket 连接断开", "Websocket disconnected"),
    USER_REPEAT_REQUEST("A0506", "用户重复请求", "User repeat request"),
    USER_RESOURCE_EXCEPTION("A0600", "用户资源异常", "User resource exception"),
    INSUFFICIENT_ACCOUNT_BALANCE("A0601", "账户余额不足", "Insufficient account balance"),
    INSUFFICIENT_DISK_SPACE_FOR_USER("A0602", "用户磁盘空间不足", "Insufficient disk space for user"),
    USER_OUT_OF_MEMORY("A0603", "用户内存空间不足", "User out of memory"),
    INSUFFICIENT_USER_OSS_CAPACITY("A0604", "用户 OSS 容量不足", "Insufficient user OSS capacity"),
    USER_QUOTA_USED_UP("A0605", "用户配额已用光", "User quota used up"),
    USER_UPLOAD_FILE_EXCEPTION("A0700", "用户上传文件异常", "User upload file exception"),
    USER_UPLOAD_FILE_TYPE_MISMATCH("A0701", "用户上传文件类型不匹配", "User upload file type mismatch"),
    USER_UPLOAD_FILE_IS_TOO_LARGE("A0702", "用户上传文件太大", "User upload file is too large"),
    USER_UPLOAD_IMAGE_IS_TOO_LARGE("A0703", "用户上传图片太大", "User upload image is too large"),
    THE_VIDEO_UPLOADED_BY_THE_USER_IS_TOO_LARGE("A0704", "用户上传视频太大", "The video uploaded by the user is too large"),
    COMPRESSED_FILE_UPLOADED_BY_USER_IS_TOO_LARGE("A0705", "用户上传压缩文件太大", "Compressed file uploaded by user is too large"),
    USERS_CURRENT_VERSION_IS_ABNORMAL("A0800", "用户当前版本异常", "User\'s current version is abnormal"),
    USER_INSTALLED_VERSION_DOES_NOT_MATCH_SYSTEM("A0801", "用户安装版本与系统不匹配", "User installed version does not match system"),
    USER_INSTALLED_VERSION_TOO_LOW("A0802", "用户安装版本过低", "User installed version too low"),
    USER_INSTALLED_VERSION_TOO_HIGH("A0803", "用户安装版本过高", "User installed version too high"),
    USER_INSTALLATION_VERSION_HAS_EXPIRED("A0804", "用户安装版本已过期", "User installation version has expired"),
    USER_API_REQUEST_VERSION_MISMATCH("A0805", "用户 API 请求版本不匹配", "User API request version mismatch"),
    USER_API_REQUEST_VERSION_TOO_HIGH("A0806", "用户 API 请求版本过高", "User API request version too high"),
    USER_API_REQUEST_VERSION_TOO_LOW("A0807", "用户 API 请求版本过低", "User API request version too low"),
    USER_PRIVACY_NOT_AUTHORIZED("A0900", "用户隐私未授权", "User privacy not authorized"),
    USER_PRIVACY_IS_NOT_SIGNED("A0901", "用户隐私未签署", "User privacy is not signed"),
    USER_CAMERA_NOT_AUTHORIZED("A0902", "用户摄像头未授权", "User camera not authorized"),
    USER_CAMERA_IS_NOT_AUTHORIZED("A0903", "用户相机未授权", "User camera is not authorized"),
    USER_PICTURE_LIBRARY_IS_NOT_AUTHORIZED("A0904", "用户图片库未授权", "User picture library is not authorized"),
    USER_FILE_IS_NOT_AUTHORIZED("A0905", "用户文件未授权", "User file is not authorized"),
    USER_LOCATION_INFORMATION_IS_NOT_AUTHORIZED("A0906", "用户位置信息未授权", "User location information is not authorized"),
    USER_ADDRESS_BOOK_IS_NOT_AUTHORIZED("A0907", "用户通讯录未授权", "User address book is not authorized"),
    ABNORMAL_USER_EQUIPMENT("A1000", "用户设备异常", "Abnormal user equipment"),
    USER_CAMERA_EXCEPTION("A1001", "用户相机异常", "User camera exception"),
    USER_MICROPHONE_IS_ABNORMAL("A1002", "用户麦克风异常", "User microphone is abnormal"),
    ABNORMAL_USER_HANDSET("A1003", "用户听筒异常", "Abnormal user handset"),
    USER_SPEAKER_IS_ABNORMAL("A1004", "用户扬声器异常", "User speaker is abnormal"),
    USERS_GPS_POSITIONING_IS_ABNORMAL("A1005", "用户GPS 定位异常", "User's GPS positioning is abnormal"),
    SYSTEM_EXECUTION_ERROR("B0001", "系统执行出错", "System execution error"),
    SYSTEM_EXECUTION_TIMEOUT("B0100", "系统执行超时", "System execution timeout"),
    SYSTEM_ORDER_PROCESSING_TIMEOUT("B0101", "系统订单处理超时", "System order processing timeout"),
    SYSTEM_DISASTER_RECOVERY_FUNCTION_IS_TRIGGERED("B0200", "系统容灾功能被触发", "System disaster recovery function is triggered"),
    SYSTEM_CURRENT_LIMITING("B0210", "系统限流", "System current limiting"),
    SYSTEM_FUNCTION_DEGRADATION("B0220", "系统功能降级", "System function degradation"),
    SYSTEM_RESOURCE_EXCEPTION("B0300", "系统资源异常", "System resource exception"),
    SYSTEM_RESOURCE_EXHAUSTION("B0310", "系统资源耗尽", "System resource exhaustion"),
    SYSTEM_DISK_SPACE_EXHAUSTED("B0311", "系统磁盘空间耗尽", "System disk space exhausted"),
    SYSTEM_OUT_OF_MEMORY("B0312", "系统内存耗尽", "System out of memory"),
    FILE_HANDLE_EXHAUSTED("B0313", "文件句柄耗尽", "File handle exhausted"),
    SYSTEM_CONNECTION_POOL_EXHAUSTED("B0314", "系统连接池耗尽", "System connection pool exhausted"),
    SYSTEM_THREAD_POOL_EXHAUSTED("B0315", "系统线程池耗尽", "System thread pool exhausted"),
    SYSTEM_RESOURCE_ACCESS_EXCEPTION("B0320", "系统资源访问异常", "System resource access exception"),
    SYSTEM_FAILED_TO_READ_DISK_FILE("B0321", "系统读取磁盘文件失败", "System failed to read disk file"),
    ERROR_CALLING_THIRD_PARTY_SERVICE("C0001", "调用第三方服务出错", "Error calling third party service"),
    MIDDLEWARE_SERVICE_ERROR("C0100", "中间件服务出错", "Middleware service error"),
    RPC_SERVICE_ERROR("C0110", "RPC 服务出错", "RPC service error"),
    RPC_SERVICE_NOT_FOUND("C0111", "RPC 服务未找到", "RPC service not found"),
    RPC_SERVICE_NOT_REGISTERED("C0112", "RPC 服务未注册", "RPC service not registered"),
    INTERFACE_DOES_NOT_EXIST("C0113", "接口不存在", "Interface does not exist"),
    MESSAGE_SERVICE_ERROR("C0120", "消息服务出错", "Message service error"),
    MESSAGE_DELIVERY_ERROR("C0121", "消息投递出错", "Message delivery error"),
    MESSAGE_CONSUMPTION_ERROR("C0122", "消息消费出错", "Message consumption error"),
    MESSAGE_SUBSCRIPTION_ERROR("C0123", "消息订阅出错", "Message subscription error"),
    MESSAGE_GROUP_NOT_FOUND("C0124", "消息分组未查到", "Message group not found"),
    CACHE_SERVICE_ERROR("C0130", "缓存服务出错", "Cache service error"),
    KEY_LENGTH_EXCEEDS_LIMIT("C0131", "key 长度超过限制", "Key length exceeds limit"),
    VALUE_LENGTH_EXCEEDS_LIMIT("C0132", "value 长度超过限制", "Value length exceeds limit"),
    STORAGE_CAPACITY_IS_FULL("C0133", "存储容量已满", "Storage capacity is full"),
    UNSUPPORTED_DATA_FORMAT("C0134", "不支持的数据格式", "Unsupported data format"),
    ERROR_CONFIGURING_SERVICE("C0140", "配置服务出错", "Error configuring service"),
    ERROR_IN_NETWORK_RESOURCE_SERVICE("C0150", "网络资源服务出错", "Error in network resource service"),
    VPN_SERVICE_ERROR("C0151", "VPN 服务出错", "VPN service error"),
    CDN_SERVICE_ERROR("C0152", "CDN 服务出错", "CDN service error"),
    DOMAIN_NAME_RESOLUTION_SERVICE_ERROR("C0153", "域名解析服务出错", "Domain name resolution service error"),
    GATEWAY_SERVICE_ERROR("C0154", "网关服务出错", "Gateway service error"),
    THIRD_PARTY_SYSTEM_EXECUTION_TIMEOUT("C0200", "第三方系统执行超时", "Third party system execution timeout"),
    RPC_EXECUTION_TIMEOUT("C0210", "RPC 执行超时", "RPC execution timeout"),
    MESSAGE_DELIVERY_TIMEOUT("C0220", "消息投递超时", "Message delivery timeout"),
    CACHE_SERVICE_TIMEOUT("C0230", "缓存服务超时", "Cache service timeout"),
    CONFIGURE_SERVICE_TIMEOUT("C0240", "配置服务超时", "Configure service timeout"),
    DATABASE_SERVICE_TIMEOUT("C0250", "数据库服务超时", "Database service timeout"),
    DATABASE_SERVICE_ERROR("C0300", "数据库服务出错", "Database service error"),
    TABLE_DOES_NOT_EXIST("C0311", "表不存在", "Table does not exist"),
    COLUMN_DOES_NOT_EXIST("C0312", "列不存在", "Column does not exist"),
    MULTIPLE_COLUMNS_WITH_THE_SAME_NAME_EXIST_IN_A_MULTI_TABLE_ASSOCIATION("C0321", "多表关联中存在多个相同名称的列", "Multiple columns with the same name exist in a multi table Association"),
    DEAD_LOCK("C0331", "数据库死锁", "dead lock"),
    PRIMARY_KEY_CONFLICT("C0341", "主键冲突", "Primary key conflict"),
    THIRD_PARTY_DISASTER_RECOVERY_SYSTEM_TRIGGERED("C0400", "第三方容灾系统被触发", "Third party disaster recovery system triggered"),
    THIRD_PARTY_SYSTEM_CURRENT_LIMITING("C0401", "第三方系统限流", "Third party system current limiting"),
    THIRD_PARTY_FUNCTION_DEGRADATION("C0402", "第三方功能降级", "Third party function degradation"),
    NOTIFICATION_SERVICE_ERROR("C0500", "通知服务出错", "Notification service error"),
    SMS_REMINDER_SERVICE_FAILED("C0501", "短信提醒服务失败", "SMS reminder service failed"),
    VOICE_ALERT_SERVICE_FAILED("C0502", "语音提醒服务失败", "Voice alert service failed"),
    EMAIL_REMINDER_SERVICE_FAILED("C0503", "邮件提醒服务失败", "Email reminder service failed");

    private String code;
    private String zhCn;
    private String zhTw;
    private String daDk;
    private String nlNl;
    private String enUs;
    private String fiFi;
    private String fiFr;
    private String deDe;
    private String itIt;
    private String jpJp;
    private String koKr;
    private String noNo;
    private String ptBr;
    private String esEs;
    private String esUs;
    private String svSe;

    private ErrorCodeEnum(String code, String zhCn, String enUs) {
        this.code = code;
        this.zhCn = zhCn;
        this.enUs = enUs;
    }

    public String getCode() {
        return code;
    }

    public String getZhCn() {
        return zhCn;
    }

    public String getEsUs() {
        return esUs;
    }

    public static final Map<String, ErrorCodeEnum> MAPS = Stream.of(ErrorCodeEnum.values()).collect(Collectors.toMap(ErrorCodeEnum::getCode, s -> s));

    public static final Map<String, ErrorCodeEnum> MAPzh = Stream.of(ErrorCodeEnum.values()).collect(Collectors.toMap(ErrorCodeEnum::getZhCn, s -> s));

    @Override
    public Map<String, ErrorCodeEnum> getMap() {
        return MAPS;
    }

    @JsonCreator
    public static ErrorCodeEnum get(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        ErrorCodeEnum errorCodeEnum = MAPS.get(value);
        if (errorCodeEnum == null) {
            return ErrorCodeEnum.valueOf(value);
        } else {
            return errorCodeEnum;
        }
    }
}
