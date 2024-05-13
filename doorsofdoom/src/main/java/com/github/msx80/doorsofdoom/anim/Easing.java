package com.github.msx80.doorsofdoom.anim;

public enum Easing {

	// from https://github.com/tweenjs/tween.js/blob/master/src/Tween.js
	LINEAR(t -> t),
	QUADRATIC_IN(t -> t*t),
	QUADRATIC_OUT(t -> t * (2f - t)),
	BOUNCE_OUT(k -> {
		if (k < (1f / 2.75f)) {
			return 7.5625f * k * k;
		} else if (k < (2f / 2.75f)) {
			return 7.5625f * (k -= (1.5f / 2.75f)) * k + 0.75f;
		} else if (k < (2.5f / 2.75f)) {
			return 7.5625f * (k -= (2.25f / 2.75f)) * k + 0.9375f;
		} else {
			return 7.5625f * (k -= (2.625f / 2.75f)) * k + 0.984375f;
		}
	}),
	BOUNCE_IN(k-> 1f-Easing.BOUNCE_OUT.fun.apply(1-k)),
	BOUNCE_INOUT(k-> {
		if (k < 0.5f) {
			return BOUNCE_IN.fun.apply(k * 2f) * 0.5f;
		}

		return BOUNCE_OUT.fun.apply(k * 2f - 1f) * 0.5f + 0.5f;
	}),
	
	;
	
	/*
	 
	Quadratic: {

		In: function (k) {

			return k * k;

		},

		Out: function (k) {

			return k * (2 - k);

		},

		InOut: function (k) {

			if ((k *= 2) < 1) {
				return 0.5 * k * k;
			}

			return - 0.5 * (--k * (k - 2) - 1);

		}

	},
	 */
	public final FloatFunction fun;

	Easing(FloatFunction fun)
	{
		this.fun = fun;
	};
	
	
}
