package club.snow.ihome.common.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The type ThreadPoolConfig.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.19
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

    private final int corePoolSize = IHomeConfig.getThreadPool().getCoreSize();

    private final int maxPoolSize = IHomeConfig.getThreadPool().getMaxSize();

    private final int queueCapacity = IHomeConfig.getThreadPool().getQueueCapacity();

    private final int keepAliveSeconds = IHomeConfig.getThreadPool().getKeepAlive();

    private final String threadNamePrefix = IHomeConfig.getThreadPool().getNamePrefix();

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
        log.info("i-Home thread pool init success! core pool size:{},max pool size:{},queue capacity:{},keep alive seconds:{},thread name prefix:{}"
                , corePoolSize, maxPoolSize, queueCapacity, keepAliveSeconds, threadNamePrefix);
        return TtlExecutors.getTtlExecutor(executor);
    }

}
