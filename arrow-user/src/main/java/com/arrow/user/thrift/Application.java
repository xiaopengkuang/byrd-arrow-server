package com.arrow.user.thrift;

import com.arrow.user.config.GlobalConfig;
import com.arrow.user.facade.service.UserService;
import com.arrow.user.service.impl.UserServiceImpl;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DESCRIPTION:
 * Created by BYRD on 25/10/2017
 * Version 0.1
 */
public class Application {
    private static final String ENV_DEV = "dev";
    private static final String ENV_PRO = "pro";
    private static final int PORT = 9801;

    /******************************************service name config begin*******************************************/
    private static String USER_SERVICE = "userService";

    /******************************************service name config end*******************************************/

    /**
     * 函数项目启动函数
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
            String env = getEnv(args);
            applicationContext.getEnvironment().setActiveProfiles(env);
            applicationContext.register(GlobalConfig.class);
            applicationContext.refresh();

            TServerSocket serverTransport = new TServerSocket(PORT);
            Factory factory = new TBinaryProtocol.Factory();
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor(USER_SERVICE,
                    new UserService.Processor<UserService.Iface>(applicationContext.getBean(UserServiceImpl.class)));

            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
            TServer server = new TThreadPoolServer(serverArgs.processor(processor).protocolFactory(factory));
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the env
     *
     * @param args
     * @return
     */
    private static String getEnv(String[] args) {
        if (null != args && args.length > 0 && ENV_PRO.equals(args[0])) {
            return ENV_PRO;
        }

        return ENV_DEV;
    }
}
