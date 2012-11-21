/**
 * Copyright (c) 2012 SMC Group. All rights reserved.
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package it.liferay.demo.temperature.util;

import java.util.Random;

/**
 * @author fpz
 *
 */
public class TemperatureGenerator {

	public static final int DEFAULT_COOLING_CYCLES = 20;
	public static final int DEFAULT_ENERGY = 20000;
	public static final int DEFAULT_MAX_THRESHOULD = 1100;
	public static final int STEP_SIZE = 20;

	public static int generate() {

		int step = _random.nextInt(STEP_SIZE);

		int t = _energy / _coolingCycles;

		int previousValue = getPreviousValue(t);

		int d = Math.abs(t - previousValue);

		int boost = 0;
		if (d > 250) {
			boost = 50;
		} else if (d > 150) {
			boost = 20;
		}

		int result = t;

		if (previousValue < t) {
			result = previousValue + (step + boost);
		}
		else {
			result = previousValue - (step + boost);
		}

		_previousValue = result;

		System.out.println("TEMPERATURE = " + result);

		return result;
	}

	public static int getCoolingCycles() {
		return _coolingCycles;
	}

	public static int getEnergy() {
		return _energy;
	}

	public static int getPreviousValue(int t) {

		if (_previousValue == 0) {
			return t;
		}
		else {
			return _previousValue;
		}
	}


	public static int getMaxThreshold() {
		return _maxThreshold;
	}

	public static void setCoolingCycles(int value) {
		System.out.println("--> New Cooling Cycles value to " + value);
		_coolingCycles = value;
	}

	public static void setEnergy(int value) {
		System.out.println("--> New Energy value to " + value);
		_energy = value;
	}

	public static void setMaxThreshold(int maxThreshold) {
		System.out.println("--> Max threshold changed from " + _maxThreshold +
			" to " + maxThreshold);
		_maxThreshold = maxThreshold;
	}

	private static int _coolingCycles = DEFAULT_COOLING_CYCLES;
	private static int _energy = DEFAULT_ENERGY;
	private static int _maxThreshold = DEFAULT_MAX_THRESHOULD;
	private static int _previousValue = 0;
	private static Random _random = new Random();

}
