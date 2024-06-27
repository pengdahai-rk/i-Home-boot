package club.snow.ihome.common.utils;

import club.snow.ihome.core.Snowflake;
import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.util.UUID;

/**
 * The type IdUtil.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.18
 */
public class IdUtil {

    private final static Snowflake SNOWFLAKE = new Snowflake();

    /**
     * Gets snowflake next id.
     *
     * @return the snowflake next id
     */
    public static long getSnowflakeNextId() {
        return SNOWFLAKE.nextId();
    }

    /**
     * Simple random uuid string.
     * 去除横杠
     *
     * @return the string
     */
    public static String simpleRandomUUID() {

        return randomUUID().replaceAll("-", "");
    }

    /**
     * Random uuid string.
     *
     * @return the string
     */
    public static String randomUUID() {

        return UUID.randomUUID().toString();
    }

    /**
     * 获取数据中心ID<br>
     * 数据中心ID依赖于本地网卡MAC地址。
     * <p>
     * 此算法来自于mybatis-plus#Sequence
     * </p>
     *
     * @param maxDatacenterId 最大的中心ID
     * @return 数据中心ID
     */
    public static long getDataCenterId(long maxDatacenterId) {
        if (maxDatacenterId == Long.MAX_VALUE) {
            maxDatacenterId -= 1;
        }
        long id = 1L;
        byte[] mac = null;
        try {
            mac = NetUtil.getLocalHardwareAddress();
        } catch (Exception ignore) {
            // ignore
        }
        if (null != mac) {
            id = ((0x000000FF & (long) mac[mac.length - 2]) | (0x0000FF00 & (((long) mac[mac.length - 1]) << 8))) >> 6;
            id = id % (maxDatacenterId + 1);
        }
        return id;
    }

    /**
     * 获取机器ID，使用进程ID配合数据中心ID生成<br>
     * 机器依赖于本进程ID或进程名的Hash值。
     *
     * <p>
     * 此算法来自于mybatis-plus#Sequence
     * </p>
     *
     * @param datacenterId 数据中心ID
     * @param maxWorkerId  最大的机器节点ID
     * @return ID
     */
    public static long getWorkerId(long datacenterId, long maxWorkerId) {
        final StringBuilder mpid = new StringBuilder();
        mpid.append(datacenterId);
        try {
            mpid.append(getPid());
        } catch (RuntimeException igonre) {
            //ignore
        }
        /*
         * MAC + PID 的 hashcode 获取16个低位
         */
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * 获取当前进程ID，首先获取进程名称，读取@前的ID值，如果不存在，则读取进程名的hash值
     *
     * @return 进程ID
     */
    private static int getPid() {
        final String processName = ManagementFactory.getRuntimeMXBean().getName();
        if (StringUtils.isBlank(processName)) {
            throw new RuntimeException("Process name is blank!");
        }
        final int atIndex = processName.indexOf('@');
        if (atIndex > 0) {
            return Integer.parseInt(processName.substring(0, atIndex));
        } else {
            return processName.hashCode();
        }
    }

}
