package com.screamingdata.hazelcastexperiment;

import com.hazelcast.core.MapLoader;
import com.hazelcast.core.MapStoreFactory;
import java.util.Properties;


// FileSystemMapStoreFactory.java
public class FileSystemMapStoreFactory implements MapStoreFactory<String, Object>
{
    @Override
    public MapLoader<String, Object> newMapStore(String mapName, Properties properties) {
        if (mapName.startsWith("c:")) mapName=mapName.substring(2); // WHY???
        return new FileSystemPersistenceProvider(mapName);
    }
}
