// Generated code from Butter Knife. Do not modify!
package com.bryderi.speedle.android.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class DashboardFragment$$ViewInjector<T extends com.bryderi.speedle.android.fragments.DashboardFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296322, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131296322, "field 'recyclerView'");
  }

  @Override public void reset(T target) {
    target.recyclerView = null;
  }
}
