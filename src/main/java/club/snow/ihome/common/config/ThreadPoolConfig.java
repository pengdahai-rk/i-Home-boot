package club.snow.ihome.common.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The type ThreadPoolConfig.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/19
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

    @Value("${i-home.thread-pool.core-size:50}")
    private int corePoolSize;

    @Value("${i-home.thread-pool.max-size:200}")
    private int maxPoolSize;

    @Value("${i-home.thread-pool.queue-capacity:1000}")
    private int queueCapacity;

    @Value("${i-home.thread-pool.keep-alive:300}")
    private int keepAliveSeconds;

    @Value("${i-home.thread-pool.name-prefix:system-thread}")
    private String threadNamePrefix;

    @Bean(name = "iHomeThreadPool")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(threadNamePrefix);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        log.info("Thread pool init success! core pool size:{},max pool size:{},queue capacity:{},keep alive seconds:{},thread name prefix:{}"
        ,corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds, threadNamePrefix);
        return TtlExecutors.getTtlExecutor(executor);
    }

}
