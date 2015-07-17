package com.bcgtgjyb.myweather.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class MyAnimation {

	public void setAnimation(Button button){
		//��ʼλ��
		float myX=button.getX();
		float myY=button.getY();
		Log.i("Animation","��ʼλ��"+myX+"++"+myY);
		//��ȡ100���漴��
		List list=getDate(10);
		Log.i("Animation", "list"+((List)list.get(0)).toString()+"***"+((List)list.get(1)).toString());
		setButtonAnimation(button, (List)list.get(0), (List)list.get(1));
		
	}
	
	public void move(Button but,float x,float y){
		float nowX=but.getX();
		float nowY=but.getY();
		AnimatorSet animatorSet=new AnimatorSet();
//		animatorSet.setDuration(10);
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(but, "translationX", x*10),//
				ObjectAnimator.ofFloat(but, "translationY", y*10));
		animatorSet.setTarget(but);
		animatorSet.start();
	}
	
	
	private void setButtonAnimation(final Button button, List listx, List listy) {
		List animationList = new ArrayList<Animator>();
		AnimatorSet myAnimation=new AnimatorSet();
		float x = 0;
		float y;
		for (int i = 0; i < listx.size(); i++) {
				y = (float) listy.get(i);
				x = (float) listx.get(i);
				AnimatorSet animatorSet = new AnimatorSet();
				animatorSet.setDuration(1500);
				Log.i("Animation","setButtonAnimation++"+x+"---"+ y);
				animatorSet.playTogether(//
						ObjectAnimator.ofFloat(button,"translationX",x), //
						ObjectAnimator.ofFloat(button, "translationY",y));//
				animationList.add(animatorSet);
			
		}
		myAnimation.playSequentially(animationList);
		myAnimation.setTarget(button);
		
		
		myAnimation.addListener(new AnimatorListener() {
			private boolean mCanceled;
			public void onAnimationStart(Animator animation) {
				mCanceled=false;

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
					
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (!mCanceled) {
				      animation.start();
				    }
				
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				mCanceled=true;
			}
		});
		myAnimation.start();

	}

	private List<List> getDate(int param) {
		List x = new ArrayList<Object>();
		List y = new ArrayList<Object>();
		float m;
		float n;
		Random random = new Random();
		// ����뾶
		float R = 30;
		float x1 =0f;
		float y1 =0f;
		float x2=0f;
		float y2=0f;
		for (int i = 0; i < param; i++) {
			x2 = (random.nextFloat()*300) % R;
			y2 = (float) Math.sqrt((R * R - x2 * x2));
			m = x2 - x1;
			n = y2 - y1;
			x.add(m);
			y.add(n);
			x2 = x1;
			y2 = y1;
		}
		m = 0f-x2;
		n = 0f-y2;
		x.add(m);
		y.add(n);
//		Log.i("Animation", x.size()+"");
		List re = new ArrayList<List>();
		re.add(x);
		re.add(y);
		return re;
	}
	
	public void setWeatherSun(Button button,float param){
		
		final AnimatorSet animatorSet=new AnimatorSet();
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(button, "alpha",1f,0.5f,1f),//
				ObjectAnimator.ofFloat(button, "scaleX",1f,1.2f,1f),//
				ObjectAnimator.ofFloat(button, "scaleY",1f,1.2f,1f)
				);
		animatorSet.setTarget(button);
		animatorSet.setDuration(3000);
		
		animatorSet.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				Log.i("Animation","onAnimationStart");
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				Log.i("Animation","onAnimationRepeat");
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				Log.i("Animation","onAnimationEnd");
				animation.start();
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				Log.i("Animation","onAnimationCancel");
				
			}
		});
		
		animatorSet.start();
		
	}

}
