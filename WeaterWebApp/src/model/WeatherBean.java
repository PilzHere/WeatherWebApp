package model;

public class WeatherBean {
	private final String cityName, countryName;

	private String cloudsStr, tempKelvin;

	public WeatherBean(final String cityName, final String countryName) {
		this.cityName = cityName;
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCloudsStr() {
		return cloudsStr;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getTempKelvin() {
		return tempKelvin;
	}

	public void setCloudsStr(final String xMLClouds) {
		this.cloudsStr = xMLClouds;
	}

	public void setTempKelvin(final String XMLTempKelvin) {
		this.tempKelvin = XMLTempKelvin;
	}
}
