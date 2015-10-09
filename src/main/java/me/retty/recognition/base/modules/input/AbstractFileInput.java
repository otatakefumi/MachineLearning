package me.retty.recognition.base.modules.input;

import javafx.util.Pair;
import me.retty.recognition.base.FileUtility;
import me.retty.recognition.base.config.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by takefumiota on 2015/09/26.
 */
public abstract class AbstractFileInput<T> extends AbstractInput<T>{
    protected List<Pair<String, String>> dataFiles;

    public AbstractFileInput(Config config) {
        super();
        dataFiles = new ArrayList<>();
        FileUtility.getPatternFilePathMap(config.getStringValue("inputDirPath", ".")).ifPresent(data -> {
            for (String key: data.keySet()) {
                data.get(key).forEach(filePath-> {
                    this.dataFiles.add(new Pair<>(key, filePath));
                });
            }
        });
        Collections.shuffle(dataFiles);
    }

    @Override
    public boolean hasNext() {
        return this.index < this.dataFiles.size();
    }

    @Override
    public Pair<String, T> get() {
        Pair<String, String> target = this.dataFiles.get(this.index);
        this.index++;
        return new Pair<>(target.getKey(), loadData(target.getValue()));
    }

    protected abstract T loadData(String dataFilePath);

    @Override
    public int count() {
        return this.dataFiles.size();
    }
}
