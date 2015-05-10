package com.bryderi.speedle.android;

import com.bryderi.speedle.android.renderers.ClassifiedRenderer;

import me.alexrs.recyclerviewrenderers.interfaces.RendererFactory;
import me.alexrs.recyclerviewrenderers.renderer.Renderer;

/**
 * Created by Mattias on 12/04/15.
 */
public class Factory implements RendererFactory {
    @Override
    public Renderer getRenderer(int id) {
        switch (id) {
            case R.layout.classified:
                return new ClassifiedRenderer(id);

        }
        return null;
    }
}
