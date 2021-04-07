package model;

import javax.servlet.http.Cookie;

public class CookieBaker {
	public static Cookie bakeCookie(final WeatherBean weatherBean) {
		final int minute = 60;

		final Cookie cookie = new Cookie(weatherBean.getCityName() + weatherBean.getCountryName(),
				weatherBean.getCityName() + "/" + weatherBean.getCountryName() + "/" + weatherBean.getTempKelvin() + "/"
						+ weatherBean.getCloudsStr().replaceAll(" ", "_"));
		cookie.setMaxAge(15 * minute);

		return cookie;
	}
}
