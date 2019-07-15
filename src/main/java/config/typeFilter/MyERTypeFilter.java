package config.typeFilter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * Created by zlj on 2019/7/16.
 */
public class MyERTypeFilter implements TypeFilter {



    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 可以从metadataReader中获取信息
        // 注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        // 类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        // 资源信息
        Resource resource = metadataReader.getResource();

        /**
         * 这里会扫描所以的类
         */
        String className = classMetadata.getClassName();
        System.out.println("当前正在扫描basePackage为 componentAnno下的类" + className);


        /**
         * 这里实现 只扫描类名字 带 'er' 的类
         */
        if (className.contains("er")) {
            return true;
        }

        return false;
    }
}
