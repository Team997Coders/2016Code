package org.usfirst.frc.team997.robot;

import java.lang.reflect.Field;

import org.usfirst.frc.team997.robot.RobotMap;


public class RobotPrefs {
	
	public static void initPrefs() {
        Field[] fields = RobotMap.Voltages.class.getDeclaredFields();
        double value = 0;

		if (fields.length > 0) {
			// for each variable we want to save to the robot...
			for (Field field : fields) {
				System.out.println("Field " + field.getName() + ":");
				try {
					value = 0;
					// see if it is already stored in the preferences map...
					if (Robot.prefs.containsKey(field.getName())) {
						// yes. then get that value
						value = Robot.prefs.getDouble(field.getName(), 0.0);
						System.out.println("found key " + field.getName() + " in preferences table = " + value);
					}
					else {
						// no.  then we need the initial value from the Init class
						// and put the key into the prefs
						String fname = field.getName();
						System.out.println("key not in prefs hash " + fname);
						try {
							Field ifield = RobotMap.InitVoltages.class.getDeclaredField(fname);
							value = ifield.getDouble(ifield.getClass());
							System.out.println("found key " + fname + " in Initial values class = " + value);
						} catch (NoSuchFieldException e) {
							System.out.println("No such field in init class " + fname);
						}
						Robot.prefs.putDouble(field.getName(), value);
					}
					field.setAccessible(true);
					field.setDouble(field.getClass(), value);
					
					if (field.getType().equals(double.class)) {
						System.out.println(field.getName() + "  ... double  = " + field.getDouble(field.getClass()));
					}
					else if (field.getType().equals(int.class)) {
						System.out.println(field.getName() + "  ... integer  = " + field.getInt(field.getClass()));
					}
					else if (field.getType().equals(boolean.class)) {
						System.out.println(field.getName() + "  ... boolean  = " + field.getBoolean(field.getClass()));
					}
					else {
						System.out.println("  ... unknown class " + field.getType());
					}
				} catch (IllegalAccessException e) {
					System.out.println("(Exception Thrown: " + e + ")");
				}
			}
		}
	}
	
	public static void readPrefs() {
		Field[] fields = RobotMap.Voltages.class.getDeclaredFields();

		if (fields.length > 0) {
			// for each variable we want to save to the robot...
			for (Field field : fields) {
				String fname = field.getName();
				System.out.println("Read Field " + fname + ":");
				try {
					field.setAccessible(true);
					
					if (field.getType().equals(double.class)) {
						double m_value = 0.0;
						m_value = Robot.prefs.getDouble(fname, 0.0);
						field.setDouble(field.getClass(), m_value);
						System.out.println(field.getName() + "  ... double = " + field.getDouble(field.getClass()));
					}
					else if (field.getType().equals(int.class)) {
						int m_value = 0;
						m_value = Robot.prefs.getInt(fname, 0);
						field.setInt(field.getClass(), m_value);
						System.out.println(field.getName() + "  ... integer  = " + field.getInt(field.getClass()));
					}
					else if (field.getType().equals(boolean.class)) {
						boolean m_value = false;
						m_value = Robot.prefs.getBoolean(fname, false);
						field.setBoolean(field.getClass(), m_value);
						System.out.println(field.getName() + "  ... set boolean  = " + field.getBoolean(field.getClass()));
					}
					else {
						System.out.println("  ... unknown class " + field.getType());
					}
				} catch (IllegalAccessException e) {
					System.out.println("(Exception Thrown: " + e + ")");
				}
			}
		}

	}

	public static void writePrefs() {
		Field[] fields = RobotMap.Voltages.class.getDeclaredFields();

		if (fields.length > 0) {
			// for each variable we want to save to the robot...
			for (Field field : fields) {
				String fname = field.getName();
				System.out.println("Write Field " + fname + ":");
				try {
					field.setAccessible(true);
					
					if (field.getType().equals(double.class)) {
						double m_value = 0.0;
						m_value = field.getDouble(fname);
						Robot.prefs.putDouble(fname, m_value);
						System.out.println(field.getName() + "  ... set double = " + Robot.prefs.getDouble(fname, 0.0));
					}
					else if (field.getType().equals(int.class)) {
						int m_value = 0;
						m_value = field.getInt(fname);
						Robot.prefs.putInt(fname,  m_value);
						System.out.println(field.getName() + "  ... set integer  = " + Robot.prefs.getInt(fname, 0));
					}
					else if (field.getType().equals(boolean.class)) {
						boolean m_value = false;
						m_value = field.getBoolean(fname);
						Robot.prefs.putBoolean(fname, m_value);
						System.out.println(field.getName() + "  ... set boolean  = " + Robot.prefs.getBoolean(fname, false));
					}
					else {
						System.out.println("  ... unknown class " + field.getType());
					}
				} catch (IllegalAccessException e) {
					System.out.println("(Exception Thrown: " + e + ")");
				}
			}
		}

	}
}