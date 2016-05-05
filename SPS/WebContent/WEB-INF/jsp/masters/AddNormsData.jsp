<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="masterLoadNormsPage" name="loadTxView" method="post"></form>

<script type="text/javascript">



var paramsObj = new Object();
var grid = null;

/*
var status_filter_store = new Ext.data.Store({
    baseParams: {ajax_command: 'exception_manager_load_status'},
    proxy: new Ext.data.HttpProxy({
        url: '/ebevent/search.ajax'
    }),
    reader: new Ext.data.JsonReader({
        root: 'status_filter',
        id: 'status_did'
    },[
        {name: 'did', mapping: 'status_did'},
        {name: 'title', mapping: 'status_i18_value'}
    ])
});

var locationtype_filter_store = new Ext.data.Store({
    baseParams: {ajax_command: 'exception_manager_loadLocationTypes'},
    proxy: new Ext.data.HttpProxy({
        url: '/ebevent/search.ajax'
    }),
    reader: new Ext.data.JsonReader({
        root: 'locationtype_filter',
        id: 'locationtype_did'
    },[
        {name: 'did', mapping: 'locationtype_did'},
        {name: 'title', mapping: 'location_type'}
    ])
});
var product_group_filter_store = new Ext.data.Store({
    baseParams:{ajax_command:'exception_manager_loadProductGroups'},
    proxy: new Ext.data.HttpProxy({
        url:'/ebevent/Search.ajax'
    }),
    reader: new Ext.data.JsonReader({
        root: 'product_group_list_filter',
        id: 'product_group_did'
    }, [
        {name: 'did', mapping: 'product_group_did'},
        {name: 'title', mapping: 'product_group'}

    ])
});
var producttype_filter_store = new Ext.data.Store({
    baseParams: {ajax_command: 'exception_manager_loadProductTypeList'},
    proxy: new Ext.data.HttpProxy({
        url: '/ebevent/search.ajax'
    }),
    reader: new Ext.data.JsonReader({
        root: 'producttype_filter_list',
        id: 'producttype_did'
    },[
        {name: 'did', mapping: 'producttype_did'},
        {name: 'title', mapping: 'product_type'}
    ])
});

var manufacturer_Store = new Ext.data.Store({
    baseParams:{ajax_command:'exception_manager_loadManufactuers'},
    proxy: new Ext.data.HttpProxy({
        url:'/ebevent/Search.ajax'
    }),
    reader: new Ext.data.JsonReader({
        root: 'manufacturers_filter',
        id: 'manufacturers_type_did'
    }, [
        {name: 'did', mapping: 'manufacturers_type_did'},
        {name: 'title', mapping: 'manufacturers_type'}

    ])
});

var exception_filter_store = new Ext.data.Store({
    baseParams:{ajax_command:'exception_manager_loadExceptionTypes'},
    proxy: new Ext.data.HttpProxy({
        url:'/ebevent/Search.ajax'
    }),
    reader: new Ext.data.JsonReader({
        root: 'exceptions_filter_list',
        id: 'exception_did'
    }, [
        {name: 'did', mapping: 'exception_did'},
        {name: 'title', mapping: 'exception_code'}

    ])
});

var owner_type_filter_store = new Ext.data.Store({
	baseParams:{ajax_command:'exception_manager_loadOwners'},
	proxy: new Ext.data.HttpProxy({
	url:'/ebevent/Search.ajax'
	}),
	reader: new Ext.data.JsonReader({
	root: 'owner_list_filter',
	id: 'owner_did'
	}, [
	{name: 'did', mapping: 'owner_did'},
	{name: 'title', mapping: 'owner_code'}
	])
});

var event_store_filter = new Ext.data.Store({
    baseParams: {ajax_command: 'event_autoevent_loadEvents'},
    proxy: new Ext.data.HttpProxy({
        url: '/ebevent/search.ajax'
    }),
    reader: new Ext.data.JsonReader({
        root: 'event_filter',
        id: 'event_did'
    },[
        {name: 'did', mapping: 'event_did'},
        {name: 'title', mapping: 'event_type'}
    ])
});

*/
var exceptionHistoryReader = new Ext.data.JsonReader({
    totalProperty: 'totalCount',
    root:'norms'
}, Ext.data.Record.create([
    {name: 'lineSecTypeId'},
    {name: 'name'},
    {name: 'uom'},
    {name: 'standardCost'}, 
    {name: 'cost'}  
    	
]));





var contextPath='sps/loadNorms.ajax';


// create the data store
var store = new Ext.data.Store({
    baseParams: {ajax_command: 'loadNorms'},   
    autoDestroy: true,
    proxy: new Ext.data.HttpProxy({
    	url: contextPath + '?ajax_command=loadNorms' // equivalent to dataUrl
    }),
    
    //work around for grouping problem when remortSorting is on
    sortOnGroup:true,
    groupBy : function(field, forceRegroup) {
        if (this.groupField == field && !forceRegroup) {
            return; // already grouped by this field
        }
        this.groupField = field;
        if (this.remoteGroup) {
            if (!this.baseParams) {
                this.baseParams = {};
            }
            this.baseParams['groupBy'] = field;
        }
        if (this.groupOnSort || this.sortOnGroup) {
            this.sort(field);
            return;
        }
        if (this.remoteGroup) {
            this.reload();
        } else {
            var si = this.sortInfo || {};
            if (si.field != field) {
                this.applySort();
            } else {
                this.sortData(field);
            }
            this.fireEvent('datachanged', this);
        }
    },
    // end
    reader: exceptionHistoryReader,
    sortInfo:{field: 'lineSecTypeId', direction: "DESC"},
    groupField:'',
    remoteSort: true
 
});

/*

function afteredit(e) {
	
	var record = grid.getStore().getAt(e.row);
    var historydid = record.get('exception_history_did');
    
	    var conn = new Ext.data.Connection();
	
	    conn.request({
	    	url:'/ebevent/search.ajax',
	        method: 'POST',
	        params: {
	            ajax_command: 'event_exceptionMgt_add_short_comments',
	            exceptionHistoryDid: historydid,
	            shortcommentValue:e.value
	        },
	        success: function(responseObj) {
	            var error = Ext.decode(responseObj.responseText).error;
	            if(error != null){
	                Ext.MessageBox.show({
	                    title: '',
	                    msg: error,
	                    buttons: Ext.MessageBox.OK,
	                    animEl: 'mb9',
	                    icon: Ext.MessageBox.ERROR
	                });
	            }
	            else{
					store.load();
	            }
	        },
	        failure: function(responseObj) {
	            Ext.MessageBox.show({
	                title: '',
	                msg: '',
	                buttons: Ext.MessageBox.OK,
	                animEl: 'mb9',
	                icon: Ext.MessageBox.ERROR
	            });
	        }
	    });
    
}
*/
Ext.onReady(function(){
	//noSplash();
  	//Ext.QuickTips.init();
  	alert("hi 1");
  	base_width_size = 900;
    base_height_size = 682;	
  	store.load();
alert("hi 2");
	/*var searchListFilters = new Ext.ux.grid.GridFilters({
	     local: false,
	     filters:[
			 {type: 'string',  dataIndex: 'location'},
	         {type: 'string',  dataIndex: 'location_type'},
	         {type: 'list',  dataIndex: 'locationKey', store:locationtype_filter_store, labelField: 'title'},
	         {type: 'string',  dataIndex: 'order_number'},
	         {type: 'string',  dataIndex: 'item_id'},
	         {type: 'string',  dataIndex: 'status'},
	         {type: 'list',  dataIndex: 'statusKey', store:status_filter_store, labelField: 'title'},	         
	         {type: 'date',  dataIndex: 'status_time'},
	         {type: 'list',  dataIndex: 'last_event', store: event_store_filter, labelField: 'title'},
	         {type: 'string',  dataIndex: 'last_event_byuser'},
	         {type: 'string',  dataIndex: 'last_event_bycompany'},
	         {type: 'list',  dataIndex: 'exception' , store:exception_filter_store, labelField: 'title'},  
	         {type: 'date',  dataIndex: 'exception_date'},
	         {type: 'string',  dataIndex: 'exception_time'},
	         {type: 'string',  dataIndex: 'item_origin'},
	         {type: 'string',  dataIndex: 'order_origin'},
	         {type: 'list',  dataIndex: 'owner_type',store: owner_type_filter_store, labelField: 'title'},
	         {type: 'string',  dataIndex: 'owner'},
	         {type: 'string',  dataIndex: 'next_destination'},
	         {type: 'list',  dataIndex: 'product_category', store: product_group_filter_store, labelField: 'title'},
	         {type: 'list',  dataIndex: 'product_type', store:producttype_filter_store, labelField: 'title'},
	         {type: 'list',  dataIndex: 'manufacturer' , store:manufacturer_Store , labelField: 'title'},
	         {type: 'string',  dataIndex: 'short_comment'},
	         {type: 'string',  dataIndex: 'comment_by'},
	         {type: 'string',  dataIndex: 'model'},
			 {type: 'string', dataIndex: 'item_location'}
	]});
*/
/*
	 var searchListFiltersP = new Ext.ux.ProgressBarPager();

	 var pageSizeCombo = new Ext.form.ComboBox({
	        name : 'pageSizeCombo',
	        width: 47,
	        store: new Ext.data.ArrayStore({
	            fields: ['id'],
	            data  : [
	                ['20'],
	                ['50'],
	                ['80'],
	                ['100']
	            ]
	        }),
	        mode : 'local',
	        value: '20',
	        listWidth     : 47,
	        triggerAction : 'all',
	        displayField  : 'id',
	        valueField    : 'id',
	        editable      : false,
	        forceSelection: true
	    });
     
 	var searchPageTbar = new Ext.PagingToolbar({
       pageSize: 20,
       store: store,
       displayInfo: true,
       items: [
               '-',
               'Per Page:',
               pageSizeCombo
           ]
       ,doLoad : function(start) {
           var o = {}, pn = this.getParams();

           var startFixed = start - (start % this.pageSize);

           o[pn.start] = startFixed;
           o[pn.limit] = this.pageSize;
           if (this.fireEvent('beforechange', this, o) !== false) {
               if (store.getCount() > 0) {
                   this.store.load({params:o});
               }
           }

           store.removeAll();
	   		
       },
 	plugins: [searchListFilters, searchListFiltersP]
   });
    
    pageSizeCombo.on('select', function(combo, record) {
    	searchPageTbar.pageSize = parseInt(record.get('id'), 10);
    	searchPageTbar.doLoad(searchPageTbar.cursor);
    }, this);
*/
    var checkboxModel = new Ext.grid.CheckboxSelectionModel({editor:{disabled:true}});


    var columns =[
             checkboxModel,
             {
                 id       :'lineSecTypeId',
                 header   : 'Line Section Type ID', 
                 sortable : true, 
                 width: 100,
                 align: 'left',
                 dataIndex: 'lineSecTypeId'
             },
             {
 				 id       :'name',
                 header   : 'Name', 
                 sortable : true,  
                 width: 100,
                 align: 'left',             
                 dataIndex: 'name'
             },
             {
 			
 				 id       :'uom',
                 header   : 'uom', 
                 sortable : true,
                 width: 100,
                 align: 'left', 
                 dataIndex: 'uom'
             },
             {
             	 id       :'standardCost',              
                 dataIndex : 'standardCost',               
                 sortable : false,
                 hidden: true,
                 hideable: false,
				 dataIndex: 'standardCost'
             },{
 				 id       :'cost',
                 header   : 'cost',  
                 sortable : true, 
                 width: 100,
                 align: 'left',
                 dataIndex: 'cost',
                 editor: new Ext.form.TextField({
					    allowBlank: true,
					    autoCreate: {tag: 'input', type: 'text', size: '30', autocomplete: 'off', maxlength: '50'}
				})
             } 
    ];



        function onItemCheck(item, checked) {
            grid.getColumnModel().setHidden(grid.getColumnModel().findColumnIndex(item.id), !checked);
        }
      	  
	    // create the Grid
    grid = new Ext.grid.EditorGridPanel({
        store: store,
        columns:columns,
        sm: checkboxModel,
        title:'Standard Norms',
       // plugins: [searchListFilters],
		 tbar: [            
            {			
            	id:'resolve',
				text:'Add Norms',
				xtype: 'button',
				ctCls: 'x-btn-over',
				type: 'submit',
				buttonAlign:'left',
				handler: function(enqResultGrid, rowIndex, e) {
					
					//alert(checkboxModel.getSelections());
	            	var orderCount = checkboxModel.getCount();
	            	var rowz = checkboxModel.getSelections();
					var orders = "";
					var exceptionAssignmnetDids = "";
					var exceptionDids = "";
					if (orderCount > 0){
						for (var index=0; index<rowz.length; index++){
							
							var ownerObjectDid = rowz[index].data.order_did;
						
							var exceptionHistoryDid = rowz[index].data.exception_history_did;
							var exceptionDid = rowz[index].data.exceptionDid;
							var exceptionAssignmentDid = rowz[index].data.exception_assignment_did;
							
							orders = orders + ownerObjectDid + "$" + tobject + "$" + exceptionHistoryDid +  "$" + exceptionDid + "|";
							exceptionAssignmnetDids = exceptionAssignmnetDids + exceptionAssignmentDid + "|";
							exceptionDids = exceptionDids + exceptionDid + "|";
						}
					}

					
				}
            },
            '-',
            
            {			
    			id:'exportDataButton',
    			text:'export',
    			iconCls:'export-icon',
    			
    		      handler: function(userGrid, rowIndex, e)
                    {
                 
                        if (grid.getStore().getAt(0)== null || grid.getStore().getAt(0) == 'undefined')
                        {
                            Ext.Msg.alert('error in header', 'error in header', function() { });

                        }
                        else
                        {

                            //paramsObjToExport = paramsObj;
                            //showQuickExportWindow('active_exception_report');
                        }
                    }
                }
        ],
  
		//bbar: searchPageTbar,
        stripeRows: true,
        autoScroll:true,
        autoWidth: true,
        overflow:'auto',
        height: 600,   
        loadMask: true,
        forceFit:true,    
        //externalMenusAttached: columnSelectMenu,
        stateful: true,
        stateId: 'grid',
        listeners: {
	        scope:this,
	        cellclick: function(grid, rowIndex, columnIndex, e) {
	            var fieldName = grid.getColumnModel().getDataIndex(columnIndex);
	            var record = grid.getStore().getAt(rowIndex);
	            if (fieldName == 'noteCount') {
	
	                var order_Did = record.get('order_did');
	                global_ownerObjectDid = order_Did;
	               
	            } 
	        }
	   }
    });

   // grid.on('afteredit', afteredit, this );

	var panel = new Ext.Panel({
	    id: 'main_panel',
	    baseCls:'x-plain',
	    style: {
	        marginRight: '5px',
	        marginLeft: '5px'
	    },
	    renderTo: 'searchForm',
	    layout:'vbox',
	    layoutConfig: {
	        align : 'stretch',
	        pack  : 'start'
	    },
	    autoScroll:true,
	    overflow:'auto',
	    height: 650,
	    // applied to child components
	    defaults: {frame:false,footer:false, border: true,bodyBorder:true,collapsible:false,shadowOffset:6},
	    resized : function() {
	        var width = (Ext.isIE) ? document.body.clientWidth : window.innerWidth;
	        var height = (Ext.isIE) ? document.body.clientHeight : window.innerHeight;
	        var grid_width = 800;
	        var grid_height = 600;
	        if (height > base_height_size) {
	            height = height - (base_height_size - grid_height); //grid height is set by the last number
	        }
	        else
	        {
	            height = grid_height+10;
	        }
	
	        if (width > base_width_size) {
	            width = Math.floor(width * 0.93)-10;
	        }
	        else
	        {
	            width = grid_width;
	        }
	
	        panel.setSize(width, height);
	        grid.setHeight(height - 330);
	        panel.doLayout('false', 'true');
	        grid.syncSize();
	    },
	    items: [grid]
	});
	
	panel.render(document.body);
	panel.resized();
 	grid.on("UIStateMgrResetCompleted", function() {
        window.document.forms['loadTxView'].submit();
    });
    grid.on("UIStateMgrRestoreCompleted", function() {
        var param = 'param';

    });

  
	    
	Ext.EventManager.onWindowResize( panel.resized, panel );
	});


</script>

<div id="searchForm">  </div>




