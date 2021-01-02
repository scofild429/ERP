package com.mypro.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {
/**
 *@ClassName DataGridView
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 09:11
 **/

    private Integer code = 0;
    private String msg = "";
    private Long count;
    private Object data;

    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGridView(Object data) {
        this.data = data;
    }
}
