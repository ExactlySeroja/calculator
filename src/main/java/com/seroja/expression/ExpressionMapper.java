package com.seroja.expression;

import com.seroja.expression.Expression;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionMapper {

        public List<Expression> mapCooks(ResultSet set) throws SQLException {
            List<Expression> cooks = new ArrayList<>();

            while (set.next()){
                Expression cook = new Expression(set.getString("expression"), set.getInt("result"));
                cooks.add(cook);
            }
            return cooks;
        }


}
