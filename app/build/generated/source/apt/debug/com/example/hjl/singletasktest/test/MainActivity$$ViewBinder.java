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

public class MainActivity$$ViewBinder<T extends MainActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends MainActivity> implements Unbinder {
    protected T target;

    private View view2131165275;

    private View view2131165276;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131165275, "field 'mainTv' and method 'onViewClicked'");
      target.mainTv = finder.castView(view, 2131165275, "field 'mainTv'");
      view2131165275 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131165276, "field 'mainJC' and method 'onViewClicked'");
      target.mainJC = finder.castView(view, 2131165276, "field 'mainJC'");
      view2131165276 = view;
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

      target.mainTv = null;
      target.mainJC = null;

      view2131165275.setOnClickListener(null);
      view2131165275 = null;
      view2131165276.setOnClickListener(null);
      view2131165276 = null;

      this.target = null;
    }
  }
}
