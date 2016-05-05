/*!
 * Ext JS Library 3.3.1
 * Copyright(c) 2006-2010 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
SampleGrid = function(limitColumns){

    function italic(value){
        return '<i>' + value + '</i>';
    }

    function change(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    }

    function pctChange(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '%</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '%</span>';
        }
        return val;
    }


    var columns = [
        {id:'company',header: "Company", width: 160, sortable: true, dataIndex: 'company'},
        {header: "Price", width: 75, sortable: true, renderer: Ext.util.Format.usMoney, dataIndex: 'price'},
        {header: "Change", width: 75, sortable: true, renderer: change, dataIndex: 'change'},
        {header: "% Change", width: 75, sortable: true, renderer: pctChange, dataIndex: 'pctChange'},
        {header: "Last Updated", width: 85, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastChange'}
    ];

    // allow samples to limit columns
    if(limitColumns){
        var cs = [];
        for(var i = 0, len = limitColumns.length; i < len; i++){
            cs.push(columns[limitColumns[i]]);
        }
        columns = cs;
    }

    SampleGrid.superclass.constructor.call(this, {
        store: new Ext.data.Store({
            reader: new Ext.data.ArrayReader({}, [
                   {name: 'Month'},
                   {name: 'payment', type: 'float'},
                   {name: 'date', type: 'float'}
                   
              ]),
            data: [
                ['3m Co',71.72,0.02],
                ['Alcoa Inc',29.01,0.42],
                ['Wal-Mart Stores, Inc.',45.45,0.73]
            ]
        }),
        columns: columns,
        autoExpandColumn: 'company',
        height:250,
        width:600
    });


}

Ext.extend(SampleGrid, Ext.grid.GridPanel);