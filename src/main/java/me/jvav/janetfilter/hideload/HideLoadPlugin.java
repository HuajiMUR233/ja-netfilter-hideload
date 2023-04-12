package me.jvav.janetfilter.hideload;

import com.janetfilter.core.plugin.MyTransformer;
import com.janetfilter.core.plugin.PluginEntry;

import java.util.ArrayList;
import java.util.List;

public class HideLoadPlugin implements PluginEntry {
    private final List<MyTransformer> transformers = new ArrayList<>();

    public HideLoadPlugin() {
        transformers.add(new ClassLoaderTransformer());
    }

    @Override
    public String getName() {
        return "HideLoadPlugin";
    }

    @Override
    public String getAuthor() {
        return "Huaji_MUR233";
    }

    @Override
    public String getVersion() {
        return "v1.0.0";
    }

    @Override
    public String getDescription() {
        return "Don't load classes from com.janetfilter";
    }

    @Override
    public List<MyTransformer> getTransformers() {
        return transformers;
    }
}
