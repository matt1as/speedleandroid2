// Generated code from Butter Knife. Do not modify!
package com.bryderi.speedle.android.viewHolders;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ViewHolderClassified$$ViewInjector<T extends com.bryderi.speedle.android.viewHolders.ViewHolderClassified> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296295, "field 'title'");
    target.title = finder.castView(view, 2131296295, "field 'title'");
  }

  @Override public void reset(T target) {
    target.title = null;
  }
}
