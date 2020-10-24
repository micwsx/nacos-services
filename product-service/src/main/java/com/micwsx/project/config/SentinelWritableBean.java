//package com.micwsx.project.config;
//
//import com.alibaba.cloud.nacos.NacosConfigProperties;
//import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
//import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
//import com.alibaba.csp.sentinel.datasource.WritableDataSource;
//import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.exception.NacosException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Properties;

/**
 * 初始化时注册sentinel中流控规则FlowRule写对象-持久化规则到nacos中数据库中
 */
//@Component
//public class SentinelWritableBean {
//
//    @Autowired
//    private NacosDiscoveryProperties nacosDiscoveryProperties;
//
//    @Autowired
//    private NacosConfigProperties nacosConfigProperties;
//
//    @PostConstruct
//    public void init(){
//        System.out.println("-------------init-----------");
//        String serverAddr = nacosDiscoveryProperties.getServerAddr();//"localhost:8488";
//        String dataId = nacosConfigProperties.getPrefix();//"shadow";
//        String group = nacosConfigProperties.getGroup();//"DEFAULT_GROUP";
//        // 读配置
//        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(serverAddr, group, dataId,
//                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
//        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
//
//        System.out.println(flowRuleDataSource.getProperty());
//        //注册写对象
//        WritableDataSource writableDataSource = new WriteableNacos<List<FlowRule>>();
//        WritableDataSourceRegistry.registerFlowDataSource(writableDataSource);
//    }
//
//    public class WriteableNacos<T> implements WritableDataSource<T> {
//        @Override
//        public void write(T value) throws Exception {
//            System.out.println("-------------write-----------");
//            String s = JSON.toJSONString(value);
//            System.out.println(s);
//            String serverAddr = nacosDiscoveryProperties.getServerAddr();//"localhost:8488";
//            String dataId = nacosConfigProperties.getPrefix();//"shadow";
//            String group = nacosConfigProperties.getGroup();//"DEFAULT_GROUP";
//            Properties properties=new Properties();
//            properties.put("serverAddr",serverAddr);
//            try {
//                ConfigService configService = NacosFactory.createConfigService(properties);
//                boolean isPublishOk = configService.publishConfig(dataId, group, s);
//                System.out.println(isPublishOk);
//            } catch (NacosException e) {
//                e.printStackTrace();
//            }
//        }
//        @Override
//        public void close() throws Exception {}
//    }
//}
