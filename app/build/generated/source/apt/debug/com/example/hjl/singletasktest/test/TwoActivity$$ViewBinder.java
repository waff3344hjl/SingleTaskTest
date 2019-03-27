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

public class TwoActivity$$ViewBinder<T extends TwoActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends TwoActivity> implements Unbinder {
    protected T target;

    private View view2131165342;

    private View view2131165343;

    private View view2131165344;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      view = finder.findRequiredView(source, 2131165342, "field 'two' and method 'onViewClicked'");
      target.two = finder.castView(view, 2131165342, "field 'two'");
      view2131165342 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131165343, "field 'two1' and method 'onViewClicked'");
      target.two1 = finder.castView(view, 2131165343, "field 'two1'");
      view2131165343 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131165344, "field 'two2' and method 'onViewClicked'");
      target.two2 = finder.castView(view, 2131165344, "field 'two2'");
      view2131165344 = view;
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

      target.two = null;
      target.two1 = null;
      target.two2 = null;

      view2131165342.setOnClickListener(null);
      view2131165342 = null;
      view2131165343.setOnClickListener(null);
      view2131165343 = null;
      view2131165344.setOnClickListener(null);
      view2131165344 = null;

      this.target = null;
    }
  }
}
