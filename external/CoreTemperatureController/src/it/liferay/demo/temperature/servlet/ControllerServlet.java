package it.liferay.demo.temperature.servlet;

import it.liferay.demo.temperature.util.TemperatureGenerator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "controller", urlPatterns = { "/controller" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerServlet() {
		super();
	}

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action.equals("temperature")) {
			_doGetTemperature(response);
		}
		else if (action.equals("get-max")) {
			_doGetMaxThreshold(response);
		}
		else if (action.equals("get-cooling-cycles")) {
			_doGetCoolingCycles(response);
		}
		else if (action.equals("get-energy")) {
			_doGetEnergy(response);
		}
		else if (action.equals("settings")) {
			int coolingCycles =
				Integer.parseInt(request.getParameter("cooling-cycles"));
			int energy = Integer.parseInt(request.getParameter("energy"));

			_doSetSettings(coolingCycles, energy);
		}
	}

	private void _doGetCoolingCycles(HttpServletResponse response)
		throws IOException {

		int value = TemperatureGenerator.getCoolingCycles();
		response.getWriter().append(String.valueOf(value));
		response.getWriter().flush();
	}

	private void _doGetEnergy(HttpServletResponse response)
		throws IOException {

		int value = TemperatureGenerator.getEnergy();
		response.getWriter().append(String.valueOf(value));
		response.getWriter().flush();
	}

	private void _doGetMaxThreshold(HttpServletResponse response)
		throws IOException {

		int value = TemperatureGenerator.getMaxThreshold();
		response.getWriter().append(String.valueOf(value));
		response.getWriter().flush();
	}

	private void _doGetTemperature(HttpServletResponse response)
		throws IOException {

		int value = TemperatureGenerator.generate();
		response.getWriter().append(String.valueOf(value));
		response.getWriter().flush();
	}

	private void _doSetSettings(int coolingCycles, int energy) {
		TemperatureGenerator.setCoolingCycles(coolingCycles);
		TemperatureGenerator.setEnergy(energy);
	}

}
