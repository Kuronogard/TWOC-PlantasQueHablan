/**
 * Copyright 2013 J. Miguel P. Tavares
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package twitter4Plants.Weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author mtavares */
public class WeatherStatusResponse extends AbstractOwmResponse {
	private final List<String> status;

	/**
	 * @param json */
	public WeatherStatusResponse (JSONObject json) {
		super (json);
		JSONObject jsonWeatherStatus = json.optJSONObject("main");
		if (jsonWeatherStatus == null) {
			this.status = Collections.emptyList ();
		} else {
			this.status = new ArrayList<String> (jsonWeatherStatus.length ());
			this.status.add(jsonWeatherStatus.optString("temp"));
			this.status.add(jsonWeatherStatus.optString("humidity"));
			this.status.add(jsonWeatherStatus.optJSONObject("clouds").optString("all"));
			
			/*
			for (int i = 0; i <jsonWeatherStatus.length (); i++) {
				jsonWeatherStatus.optJSON
				JSONObject jsonStatus = jsonWeatherStatus.optJSONObject (i);
				if (jsonStatus != null) {
					this.status.add (new StatusWeatherData (jsonStatus));
				}
			}
			*/
		}
	}

	public boolean hasWeatherStatus () {
		return this.status != null && !this.status.isEmpty ();
	}
	public List<String> getWeatherStatus () {
		return this.status;
	}
}
