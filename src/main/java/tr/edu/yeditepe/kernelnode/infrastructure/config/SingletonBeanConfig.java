package tr.edu.yeditepe.kernelnode.infrastructure.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tr.edu.yeditepe.kernelnode.domain.model.blockchain.BlockChain;
import tr.edu.yeditepe.kernelnode.domain.model.consentchain.ConsentChain;

@Configuration
public class SingletonBeanConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public BlockChain blockChain() {
        return new BlockChain();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ConsentChain consentChain() {
        return new ConsentChain();
    }
}
