// Generated code from Butter Knife. Do not modify!
package com.example.hjl.singletasktest.hybird;

import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class Hy2$$ViewBinder<T extends Hy2> implements ViewBinder<T> {
  @Override
  public Unbinder bind(Finder finder, T target, Object source) {
    return new InnerUnbinder<>(target, finder, source);
  }

  protected static class InnerUnbinder<T extends Hy2> implements Unbinder {
    protected T target;

    protected InnerUnbinder(T target, Finder finder, Object source) {
      this.target = target;

      target.webview = finder.findRequiredViewAsType(source, 2131165348, "field 'webview'", WebView.class);
    }

    @Override
    public void unbind() {
      T target = this.target;
      if (target == null) throw new IllegalStateException("Bindings already cleared.");

      target.webview = null;

      this.target = null;
    }
  }
}
