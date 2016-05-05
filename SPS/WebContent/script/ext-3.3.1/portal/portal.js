/*!
 * Ext JS Library 3.3.1
 * Copyright(c) 2006-2010 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
Ext.onReady(function(){

    // NOTE: This is an example showing simple state management. During development,
    // it is generally best to disable state management as dynamically-generated ids
    // can change across page loads, leading to unpredictable results.  The developer
    // should ensure that stable state ids are set for stateful components in real apps.
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    // create some portlet tools using built in Ext tool ids
    var tools = [{
        id:'gear',
        handler: function(){
            Ext.Msg.alert('Message', 'The Settings tool was clicked.');
        }
    },{
        id:'close',
        handler: function(e, target, panel){
            panel.ownerCt.remove(panel, true);
        }
    }];

    var viewport = new Ext.Viewport({
        layout:'border',
        items:[{
            region:'east',
            id:'east-panel',
            title:'West',
            split:true,
            width: 200,
            minSize: 175,
            maxSize: 400,
            collapsible: true,
            margins:'35 0 5 5',
            cmargins:'35 5 5 5',
            layout:'accordion',
            layoutConfig:{
                animate:true
            },
            items: [{
                //html: Ext.example.shortBogusMarkup,
                title:'Chat Application',
                autoScroll:true,
                border:false,
                iconCls:'nav'
            }]
        },{
            xtype:'portal',
            region:'center',
            margins:'35 5 5 0',
            items:[{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'View Monthly Bills',
                    layout:'fit',
                    tools: tools,
                    items: new SampleGrid([0, 2, 3])
                },{
                    title: 'Billing Reporting',
                    tools: tools,
					items:[
						   new Ext.form.ComboBox({
				        	                        id: 'month',
				        	                        name: 'month',
				        	                        fieldLabel: 'Month',
				        	                        //store: locationtype_store,
				        	                        
				        	                        //editable: false,
													store: new Ext.data.ArrayStore({
													id: 0,
													fields: [
														'did',
														'title'
													],
													data: [
                									[1,'January'],
                									[2,'February'],
                									[3,'March']
													]
												}),
													displayField: 'title',
				        	                        valueField: 'did',

				        	                        mode: 'local',
				        	                        triggerAction: 'all',
				        	                        emptyText:'Select a Month',
				        	                        //selectOnFocus:false,
				        	                        resizable:true,
				        	                        //tabIndex:1,
				        	                        minListWidth:150,
				        	                        allowBlank:true
				        	    })
						   ],
                    buttons: [ 
							   {
								   
							   text: 'Report Generation', handler: function() {                        
							        Ext.Ajax.request({
                                       url : 'horseapp/userLogin.ajax?ajax_command=reportGeneration',
                                       method: 'POST',
                                       //params :{
                                           //uname:uname,
                                           //pwd:pwd
                                      // },
                                       success: function ( result, request ) {
                                           var status = Ext.util.JSON.decode(result.responseText).status;
                                           var month = Ext.getCmp('month').getValue();
                                           Ext.Msg.alert(' ', month, function() {
                                           })
   
                                           if(status == 'success') {
                                           
                                        	   var userName = Ext.util.JSON.decode(result.responseText).data.username;
                                        	   location.href = "http://localhost:8180/SriReport/frameset?__report=CustomerBill.rptdesign&phoneNumber="+userName+"&ReportMonth="+month;
                                           } else if(status == 'failure') {
                                               Ext.Msg.alert('Error', 'Login failed due to Internal Error!', function() {
                                               })
                                           }

                                       },
                                       failure: function ( result, request ) {
                                           Ext.Msg.alert(' ', 'Login failed due to Internal Error!', function() {
                                           })
                                       }
                                   });                 
							   }                  
							   }                
								   ] 
					
                    
                }]
            },{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Billing',
                    tools: tools,
					/* buttons: [ 
							   {
								   
							   text: 'Enable SMS Service', handler: function() {                        
								   Ext.Ajax.request({
                                       url : 'horseapp/userLogin.ajax?ajax_command=enableSmsService',
                                       method: 'POST',
                                      
                                       success: function ( result, request ) {
                                           var status = Ext.util.JSON.decode(result.responseText).status;

                                           if(status == 'success') {
                                           	//location.href = "js/ext-3.3.1/portal/portal.html";
                                           	 //Ext.Msg.alert('Status', Ext.util.JSON.decode(result.responseText).data.result,
                                        	   Ext.Msg.alert(' ', Ext.util.JSON.decode(result.responseText).data.result, function() {
                                               })
                                           } else if(status == 'failure') {
                                               //Ext.Msg.alert('Error', 'Login failed due to Internal Error!', function() {
                                               //})
                                           }

                                       },
                                       failure: function ( result, request ) {
                                           Ext.Msg.alert(' ', 'Login failed due to Internal Error!', function() {
                                           })
                                       }
                                   });             
							   }                  
							   }, 
							   {                      
							   text:'Disable SMS Service', handler: function() {
								   designWindow.hide();                                                     
								   //callCustomWindow();                      
								   }                  
								   }                 
								   ] */
                    //html: Ext.example.shortBogusMarkup
                },{
                    title: 'Services',
                    tools: tools,
	 				buttons: [ 
							   {
								   
							   text: 'Enable SMS Service', handler: function() {                        
								   Ext.Ajax.request({
                                       url : 'horseapp/userLogin.ajax?ajax_command=enableSmsService',
                                       method: 'POST',
                                      
                                       success: function ( result, request ) {
                                           var status = Ext.util.JSON.decode(result.responseText).status;

                                           if(status == 'success') {
                                           	//location.href = "js/ext-3.3.1/portal/portal.html";
                                           	 //Ext.Msg.alert('Status', Ext.util.JSON.decode(result.responseText).data.result,
                                        	   Ext.Msg.alert(' ', Ext.util.JSON.decode(result.responseText).data.result, function() {
                                               })
                                           } else if(status == 'failure') {
                                               //Ext.Msg.alert('Error', 'Login failed due to Internal Error!', function() {
                                               //})
                                           }

                                       },
                                       failure: function ( result, request ) {
                                           Ext.Msg.alert(' ', 'Login failed due to Internal Error!', function() {
                                           })
                                       }
                                   });             
							   }                  
							   }, 
							   {                      
							   text:'Disable SMS Service', handler: function() {
								   Ext.Ajax.request({
                                       url : 'horseapp/userLogin.ajax?ajax_command=disableSmsService',
                                       method: 'POST',
                                      
                                       success: function ( result, request ) {
                                           var status = Ext.util.JSON.decode(result.responseText).status;

                                           if(status == 'success') {
                                           	// location.href =
											// "js/ext-3.3.1/portal/portal.html";
                                           	 // Ext.Msg.alert('Status',
												// Ext.util.JSON.decode(result.responseText).data.result,
                                        	   Ext.Msg.alert(' ', Ext.util.JSON.decode(result.responseText).data.result, function() {
                                               })
                                           } else if(status == 'failure') {
                                               // Ext.Msg.alert('Error', 'Login
												// failed due to Internal
												// Error!', function() {
                                               // })
                                           }

                                       },
                                       failure: function ( result, request ) {
                                           Ext.Msg.alert(' ', 'Login failed due to Internal Error!', function() {
                                           })
                                       }
                                   });                      
								   }                  
								   }                 
								   ] 
 
                    //html: Ext.example.shortBogusMarkup
                }]
            },{
                columnWidth:.33,
                style:'padding:10px',
                items:[{
                    title: 'Panel 3',
                    tools: tools,
                    //html: Ext.example.shortBogusMarkup
                },{
                    title: 'Another Panel 3',
                    tools: tools,
                    //html: Ext.example.shortBogusMarkup
                }]
            }]
            
            /*
             * Uncomment this block to test handling of the drop event. You could use this
             * to save portlet position state for example. The event arg e is the custom 
             * event defined in Ext.ux.Portal.DropZone.
             */
//            ,listeners: {
//                'drop': function(e){
//                    Ext.Msg.alert('Portlet Dropped', e.panel.title + '<br />Column: ' + 
//                        e.columnIndex + '<br />Position: ' + e.position);
//                }
//            }
        }]
    });
});

