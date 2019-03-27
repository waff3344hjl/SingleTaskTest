// Generated code from Butter Knife. Do not modify!
package com.example.hjl.singletasktest.pic2;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ScAndShowActivity$$ViewBinder<T extends ScAndShowActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends ScAndShowActivity> implements Unbinder {
    protected T target;

    private View view2131165353;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131165353, "field 'webviewOther' and method 'onViewClicked'");
      target.webviewOther = finder.castView(view, 2131165353, "field 'webviewOther'");
      view2131165353 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked();
        }
      });
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.webviewOther = null;

      view2131165353.setOnClickListener(null);
      view2131165353 = null;

      this.target = null;
    }
  }
}
