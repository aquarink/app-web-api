package models;

import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.event.ServerConfigStartup;

public class DokuServerConfigStartup implements ServerConfigStartup {
    @Override
    public void onStart(ServerConfig serverConfig) {
//        serverConfig.setDatabaseSequenceBatchSize(1);
        serverConfig.setEncryptKeyManager(new DokuEncryptKeyManager()); 
    }
}