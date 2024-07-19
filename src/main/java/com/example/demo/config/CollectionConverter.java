package com.example.demo.config;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionConverter<S, D> implements Converter<Collection<S>, List<D>> {

    @Override
    public List<D> convert(MappingContext<Collection<S>, List<D>> context) {
        Collection<S> source = context.getSource();
        List<D> destination = new ArrayList<>();
        if (source != null) {
            for (S item : source) {
                destination.add(context.getMappingEngine().map(context.create(item, (Class<D>) context.getDestinationType().getComponentType())));
            }
        }
        return destination;
    }
}

