package com.example.bossrebuild.widge.flow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;


public class FlowLayout extends ViewGroup {
	private int mHorizontalSpacing = dip2px(11);
	private int mVerticalSpacing = dip2px(9);
	boolean mNeedLayout = true;
	private int mUsedWidth = 0;
	private final List<Line> mLines = new ArrayList<Line>();
	private Line mLine = null;
	private int mMaxLinesCount = 100;

	public FlowLayout(Context context) {
		super(context);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec)- getPaddingRight() - getPaddingLeft();
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec)- getPaddingTop() - getPaddingBottom();
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		restoreLine();
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() == GONE) {
				continue;
			}
			int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(sizeWidth,
					modeWidth == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST: modeWidth);
			int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
					sizeHeight,
					modeHeight == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST: modeHeight);
			child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

			if (mLine == null) {
				mLine = new Line();
			}
			int childWidth = child.getMeasuredWidth();
			mUsedWidth += childWidth;
			if (mUsedWidth <= sizeWidth) {
				mLine.addView(child);
				mUsedWidth += mHorizontalSpacing;
				if (mUsedWidth >= sizeWidth) {
					if (!newLine()) {
						break;
					}
				}
			} else {
				if (mLine.getViewCount() == 0) {
					mLine.addView(child);
					if (!newLine()) {
						break;
					}
				} else {
					if (!newLine()) {
						break;
					}
					mLine.addView(child);
					mUsedWidth += childWidth + mHorizontalSpacing;
				}
			}
		}

		if (mLine != null && mLine.getViewCount() > 0 && !mLines.contains(mLine)) {
			mLines.add(mLine);
		}

		int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
		int totalHeight = 0;
		final int linesCount = mLines.size();
		for (int i = 0; i < linesCount; i++) {
			totalHeight += mLines.get(i).mHeight;
		}
		totalHeight += mVerticalSpacing * (linesCount - 1);
		totalHeight += getPaddingTop() + getPaddingBottom();
		setMeasuredDimension(totalWidth,resolveSize(totalHeight, heightMeasureSpec));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (!mNeedLayout || changed) {
			mNeedLayout = false;
			int left = getPaddingLeft();
			int top = getPaddingTop();
			final int linesCount = mLines.size();
			for (int i = 0; i < linesCount; i++) {
				final Line oneLine = mLines.get(i);
				oneLine.layoutView(left, top);
				top += oneLine.mHeight + mVerticalSpacing;
			}
		}
	}


	private void restoreLine() {
		mLines.clear();
		mLine = new Line();
		mUsedWidth = 0;
	}


	private boolean newLine() {
		mLines.add(mLine);
		if (mLines.size() < mMaxLinesCount) {
			mLine = new Line();
			mUsedWidth = 0;
			return true;
		}
		return false;
	}


	class Line {
		int mWidth = 0;
		int mHeight = 0;
		List<View> views = new ArrayList<View>();

		public void addView(View view) {
			views.add(view);
			mWidth += view.getMeasuredWidth();
			int childHeight = view.getMeasuredHeight();
			mHeight = mHeight < childHeight ? childHeight : mHeight;
		}

		public int getViewCount() {
			return views.size();
		}

		public void layoutView(int l, int t) {
			int left = l;
			int top = t;
			int count = getViewCount();
			int layoutWidth = getMeasuredWidth() - getPaddingLeft()- getPaddingRight();
			int surplusWidth = layoutWidth - mWidth - mHorizontalSpacing* (count - 1);
			if (surplusWidth >= 0) {
				int splitSpacing = (int) (surplusWidth / count + 0.5);
				for (int i = 0; i < count; i++) {
					final View view = views.get(i);
					int childWidth = view.getMeasuredWidth();
					int childHeight = view.getMeasuredHeight();
					int topOffset = (int) ((mHeight - childHeight) / 2.0 + 0.5);
					if (topOffset < 0) {
						topOffset = 0;
					}
					childWidth = childWidth + splitSpacing;
					view.getLayoutParams().width = childWidth;
					if (splitSpacing > 0) {
						int widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
						int heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
						view.measure(widthMeasureSpec, heightMeasureSpec);
					}
					view.layout(left, top + topOffset, left + childWidth, top+ topOffset + childHeight);
					left += childWidth + mHorizontalSpacing;
				}
			} else {
				if (count == 1) {
					View view = views.get(0);
					view.layout(left, top, left + view.getMeasuredWidth(), top+ view.getMeasuredHeight());
				}
			}
		}
	}

	public int dip2px(float dp) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (density * dp + 0.5);
	}
}
