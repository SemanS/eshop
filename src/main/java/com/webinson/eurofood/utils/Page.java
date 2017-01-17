package com.webinson.eurofood.utils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Slavo on 1/17/2017.
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<T> content;

    private final int numberOfElements;

    private final long count;

    public Page(final List<T> content, final long count) {
        this.content = content == null ? Collections.<T>emptyList() : content;
        this.numberOfElements = content == null ? 0 : content.size();
        this.count = count;
    }

    public List<T> getContent() {
        return content;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public long getRowCount() {
        return count;
    }

}
