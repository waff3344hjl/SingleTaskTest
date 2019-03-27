// Generated code from Butter Knife. Do not modify!
package com.example.hjl.singletasktest.test;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FirstActivity$$ViewBinder<T extends FirstActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends FirstActivity> implements Unbinder {
    protected T target;

    private View view2131165286;

    private View view2131165287;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131165286, "field 'one' and method 'onViewClicked'");
      target.one = finder.castView(view, 2131165286, "field 'one'");
      view2131165286 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131165287, "field 'one1' and method 'onViewClicked'");
      target.one1 = finder.castView(view, 2131165287, "field 'one1'");
      view2131165287 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.one = null;
      target.one1 = null;

      view2131165286.setOnClickListener(null);
      view2131165286 = null;
      view2131165287.setOnClickListener(null);
      view2131165287 = null;

      this.target = null;
    }
  }
}
