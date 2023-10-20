/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ariel
 */
public class SequenceManager {
    private final ConectorBD conector;

    public SequenceManager(ConectorBD conector) {
        this.conector = conector;
    }

    public int getValue(String sequenceName) {
        int nextValue = 0;
        String sql = "SELECT nextval('" + sequenceName + "')";

        try {
            ResultSet resultSet = conector.getSelect(sql);
            if (resultSet.next()) {
                nextValue = resultSet.getInt(1);
            }
            conector.close(resultSet);
        } catch (SQLException e) {
        }

        return nextValue;
    }
}
