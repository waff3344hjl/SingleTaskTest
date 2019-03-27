// Generated code from Butter Knife. Do not modify!
package com.example.hjl.singletasktest.hybird;

import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class HybirdActivity$$ViewBinder<T extends HybirdActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends HybirdActivity> implements Unbinder {
    protected T target;

    private View view2131165219;

    private View view2131165220;

    private View view2131165221;

    private View view2131165222;

    protected InnerUnbinder(final T target, Finder finder, Object source) {
      this.target = target;

      View view;
      target.webView = finder.findRequiredViewAsType(source, 2131165352, "field 'webView'", WebView.class);
      view = finder.findRequiredView(source, 2131165219, "field 'bt1' and method 'onViewClicked'");
      target.bt1 = finder.castView(view, 2131165219, "field 'bt1'");
      view2131165219 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131165220, "field 'bt2' and method 'onViewClicked'");
      target.bt2 = finder.castView(view, 2131165220, "field 'bt2'");
      view2131165220 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131165221, "field 'bt3' and method 'onViewClicked'");
      target.bt3 = finder.castView(view, 2131165221, "field 'bt3'");
      view2131165221 = view;
      view.setOnClickListener(new DebouncingOnClickListener() {
        @Override
        public void doClick(View p0) {
          target.onViewClicked(p0);
        }
      });
      view = finder.findRequiredView(source, 2131165222, "field 'bt4' and method 'onViewClicked'");
      target.bt4 = finder.castView(view, 2131165222, "field 'bt4'");
      view2131165222 = view;
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

      target.webView = null;
      target.bt1 = null;
      target.bt2 = null;
      target.bt3 = null;
      target.bt4 = null;

      view2131165219.setOnClickListener(null);
      view2131165219 = null;
      view2131165220.setOnClickListener(null);
      view2131165220 = null;
      view2131165221.setOnClickListener(null);
      view2131165221 = null;
      view2131165222.setOnClickListener(null);
      view2131165222 = null;

      this.target = null;
    }
  }
}
