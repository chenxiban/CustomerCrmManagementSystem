/**
 * edatagrid - jQuery EasyUI
 * 
 * Licensed under the GPL:
 *   http://www.gnu.org/licenses/gpl.txt
 *
 * Copyright 2011 stworthy [ stworthy@gmail.com ] 
 * 
 * Dependencies:
 *   datagrid
 *   messager
 * 
 */
var loginIds = window.localStorage.getItem("loginId"); // 登录用户的id
(function($) {
	var currTarget;
	$(function() {
		$(document).unbind('.edatagrid').bind('mousedown.edatagrid', function(e) {
			var p = $(e.target).closest('div.datagrid-view,div.combo-panel');
			if(p.length) {
				if(p.hasClass('datagrid-view')) {
					var dg = p.children('table');
					if(dg.length && currTarget != dg[0]) {
						_save();
					}
				}
				return;
			}
			_save();

			function _save() {
				var dg = $(currTarget);
				if(dg.length) {
					dg.edatagrid('saveRow');
					currTarget = undefined;
				}
			}
		});
	});

	function buildGrid(target) {
		var opts = $.data(target, 'edatagrid').options;
		$(target).datagrid($.extend({}, opts, {
			onDblClickCell: function(index, field) {
				if(opts.editing) {
					$(this).edatagrid('editRow', index);
					focusEditor(field);
				}
			},
			onClickCell: function(index, field) {
				if(opts.editing && opts.editIndex >= 0) {
					$(this).edatagrid('editRow', index);
					focusEditor(field);
				}
			},
			onAfterEdit: function(index, row) {
				opts.editIndex = -1;
				var url = row.isNewRecord ? opts.saveUrl : opts.updateUrl; //updateUrl删除的方法
				console.log(row); //在控制台输出

				var myrow = { //myrow表示需要增删改的参数
					stuName: row.stuName,
					stuSex: row.stuSex,
					createName: row.createName,
					name: row.name,
					protectEMail: row.protectEMail,
					protectMTel: row.protectMTel,
					loginName: row.loginName,
					id: row.id
				};
				if(url) {
					$.post(url, myrow, function(data) {
						if(data.success) {
							$.messager.alert({
								title: '提示信息',
								msg: data.message
							});
							$('#dg').edatagrid('clearSelections');
							$("#dg").edatagrid("reload"); //重新加载
						} else {
							$.messager.alert({
								title: '提示信息',
								msg: data.message
							});
							$(target).edatagrid('cancelRow', index);
							$(target).edatagrid('selectRow', index);
							$(target).edatagrid('editRow', index);
							opts.onError.call(target, index, data);
							return;
						}
						data.isNewRecord = null;
						$(target).datagrid('updateRow', {
							index: index,
							row: data
						});
						if(opts.tree) {
							var idValue = row[opts.idField || 'id'];
							var t = $(opts.tree);
							var node = t.tree('find', idValue);
							if(node) {
								node.text = row[opts.treeTextField];
								t.tree('update', node);
							} else {
								var pnode = t.tree('find', row[opts.treeParentField]);
								t.tree('append', {
									parent: (pnode ? pnode.target : null),
									data: [{
										id: idValue,
										text: row[opts.treeTextField]
									}]
								});
							}
						}
						opts.onSave.call(target, index, row);
					}, 'json');
				} else {
					opts.onSave.call(target, index, row);
				}
				if(opts.onAfterEdit) opts.onAfterEdit.call(target, index, row);
			},
			onCancelEdit: function(index, row) {
				opts.editIndex = -1;
				if(row.isNewRecord) {
					$(this).datagrid('deleteRow', index);
				}
				if(opts.onCancelEdit) opts.onCancelEdit.call(target, index, row);
			},
			onBeforeLoad: function(param) {
				if(opts.onBeforeLoad.call(target, param) == false) {
					return false
				}
				//				$(this).datagrid('rejectChanges');
				$(this).edatagrid('cancelRow');
				if(opts.tree) {
					var node = $(opts.tree).tree('getSelected');
					param[opts.treeParentField] = node ? node.id : undefined;
				}

			}
		}));

		function focusEditor(field) {
			var editor = $(target).datagrid('getEditor', {
				index: opts.editIndex,
				field: field
			});
			if(editor) {
				editor.target.focus();
			} else {
				var editors = $(target).datagrid('getEditors', opts.editIndex);
				if(editors.length) {
					editors[0].target.focus();
				}
			}
		}

		if(opts.tree) {
			$(opts.tree).tree({
				url: opts.treeUrl,
				onClick: function(node) {
					$(target).datagrid('load');
				},
				onDrop: function(dest, source, point) {
					var targetId = $(this).tree('getNode', dest).id;
					$.ajax({
						url: opts.treeDndUrl,
						type: 'post',
						data: {
							id: source.id,
							targetId: targetId,
							point: point
						},
						dataType: 'json',
						success: function() {
							$(target).datagrid('load');
						}
					});
				}
			});
		}
	}

	$.fn.edatagrid = function(options, param) {
		if(typeof options == 'string') {
			var method = $.fn.edatagrid.methods[options];
			if(method) {
				return method(this, param);
			} else {
				return this.datagrid(options, param);
			}
		}

		options = options || {};
		return this.each(function() {
			var state = $.data(this, 'edatagrid');
			if(state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'edatagrid', {
					options: $.extend({}, $.fn.edatagrid.defaults, $.fn.edatagrid.parseOptions(this), options)
				});
			}
			buildGrid(this);
		});
	};

	$.fn.edatagrid.parseOptions = function(target) {
		return $.extend({}, $.fn.datagrid.parseOptions(target), {});
	};

	$.fn.edatagrid.methods = {
		options: function(jq) {
			var opts = $.data(jq[0], 'edatagrid').options;
			return opts;
		},
		enableEditing: function(jq) {
			return jq.each(function() {
				var opts = $.data(this, 'edatagrid').options;
				opts.editing = true;
			});
		},
		disableEditing: function(jq) {
			return jq.each(function() {
				var opts = $.data(this, 'edatagrid').options;
				opts.editing = false;
			});
		},
		editRow: function(jq, index) {
			return jq.each(function() {
				var dg = $(this);
				var opts = $.data(this, 'edatagrid').options;
				var editIndex = opts.editIndex;
				if(editIndex != index) {
					if(dg.datagrid('validateRow', editIndex)) {
						if(editIndex >= 0) {
							if(opts.onBeforeSave.call(this, editIndex) == false) {
								setTimeout(function() {
									dg.datagrid('selectRow', editIndex);
								}, 0);
								return;
							}
						}
						dg.datagrid('endEdit', editIndex);
						dg.datagrid('beginEdit', index);
						opts.editIndex = index;

						if(currTarget != this && $(currTarget).length) {
							$(currTarget).edatagrid('saveRow');
							currTarget = undefined;
						}
						if(opts.autoSave) {
							currTarget = this;
						}

						var rows = dg.datagrid('getRows');
						opts.onEdit.call(this, index, rows[index]);
					} else {
						setTimeout(function() {
							dg.datagrid('selectRow', editIndex);
						}, 0);
					}
				}
			});
		},
		addRow: function(jq, index) {
			return jq.each(function() {
				var dg = $(this);
				var opts = $.data(this, 'edatagrid').options;
				if(opts.editIndex >= 0) {
					if(!dg.datagrid('validateRow', opts.editIndex)) {
						dg.datagrid('selectRow', opts.editIndex);
						return;
					}
					if(opts.onBeforeSave.call(this, opts.editIndex) == false) {
						setTimeout(function() {
							dg.datagrid('selectRow', opts.editIndex);
						}, 0);
						return;
					}
					dg.datagrid('endEdit', opts.editIndex);
				}
				var rows = dg.datagrid('getRows');

				function _add(index, row) {
					if(index == undefined) {
						dg.datagrid('appendRow', row);
						opts.editIndex = rows.length - 1;
					} else {
						dg.datagrid('insertRow', {
							index: index,
							row: row
						});
						opts.editIndex = index;
					}
				}
				if(typeof index == 'object') {
					_add(index.index, $.extend(index.row, {
						isNewRecord: true
					}))
				} else {
					_add(index, {
						isNewRecord: true
					});
				}

				//				if (index == undefined){
				//					dg.datagrid('appendRow', {isNewRecord:true});
				//					opts.editIndex = rows.length - 1;
				//				} else {
				//					dg.datagrid('insertRow', {
				//						index: index,
				//						row: {isNewRecord:true}
				//					});
				//					opts.editIndex = index;
				//				}

				dg.datagrid('beginEdit', opts.editIndex);
				dg.datagrid('selectRow', opts.editIndex);

				if(opts.tree) {
					var node = $(opts.tree).tree('getSelected');
					rows[opts.editIndex][opts.treeParentField] = (node ? node.id : 0);
				}

				opts.onAdd.call(this, opts.editIndex, rows[opts.editIndex]);
			});
		},
		saveRow: function(jq) {
			return jq.each(function() {
				var dg = $(this);
				var opts = $.data(this, 'edatagrid').options;
				if(opts.editIndex >= 0) {
					if(opts.onBeforeSave.call(this, opts.editIndex) == false) {
						setTimeout(function() {
							dg.datagrid('selectRow', opts.editIndex);
						}, 0);
						return;
					}
					$(this).datagrid('endEdit', opts.editIndex);
				}
			});
		},
		cancelRow: function(jq) {
			return jq.each(function() {
				var opts = $.data(this, 'edatagrid').options;
				if(opts.editIndex >= 0) {
					$(this).datagrid('cancelEdit', opts.editIndex);
					$(this).edatagrid("load"); //重新加载
				}

			});
		},
		destroyRow: function(jq, index) {
			return jq.each(function() {
				var dg = $(this);
				var opts = $.data(this, 'edatagrid').options;

				var rows = [];
				if(index == undefined) {
					rows = dg.datagrid('getSelections');
				} else {
					var rowIndexes = $.isArray(index) ? index : [index];
					for(var i = 0; i < rowIndexes.length; i++) {
						var row = opts.finder.getRow(this, rowIndexes[i]);
						if(row) {
							rows.push(row);
						}
					}
				}

				if(!rows.length) {
					$.messager.show({
						title: opts.destroyMsg.norecord.title,
						msg: opts.destroyMsg.norecord.msg
					});
					return;
				}

				$.messager.confirm(opts.destroyMsg.confirm.title, opts.destroyMsg.confirm.msg, function(r) {
					if(r) {
						for(var i = 0; i < rows.length; i++) {
							_del(rows[i]);
						}
						dg.datagrid('clearSelections');
					}
				});

				function _del(row) {
					var index = dg.datagrid('getRowIndex', row);
					if(index == -1) {
						return
					}
					if(row.isNewRecord) {
						dg.datagrid('cancelEdit', index);
					} else {
						if(opts.destroyUrl) {
							var idValue = row[opts.idField || 'id'];
							if(idValue == loginIds) {
								$.messager.alert({
									title: '删除失败',
									msg: '无法删除当前登录角色!',
									timeout: 3000, //推出时间
									showType: 'slide', //动画效果
									showSpeed: 3000
								});
								return;
							}
							$.post(opts.destroyUrl, { //删除的方法
								id: idValue,
								roleId: idValue
							}, function(data) {
								var index = dg.datagrid('getRowIndex', idValue);
								if(!data.success) { //删除的时候的错误的提示
									$.messager.alert({
										title: '提示信息',
										msg: data.message
									});
									return;
									/*dg.datagrid('selectRow', index);
									opts.onError.call(dg[0], index, data);*/
								} else { //删除的错误的提示
									$.messager.alert({
										title: '提示信息',
										msg: data.message
									});
									$('#dg').edatagrid('clearSelections');
									$("#dg").edatagrid("reload"); //重新加载
									dg.datagrid('selectRow', index);
									opts.onError.call(dg[0], index, data);
								}
								if(opts.tree) {
									dg.datagrid('reload');
									var t = $(opts.tree);
									var node = t.tree('find', idValue);
									if(node) {
										t.tree('remove', node.target);
									}
								} else {
									dg.datagrid('cancelEdit', index);
									dg.datagrid('deleteRow', index);
								}
								opts.onDestroy.call(dg[0], index, row);
							}, 'json');
						} else {
							dg.datagrid('cancelEdit', index);
							dg.datagrid('deleteRow', index);
							opts.onDestroy.call(dg[0], index, row);
						}
					}
				}
			});
		}
	};

	$.fn.edatagrid.defaults = $.extend({}, $.fn.datagrid.defaults, {
		editing: true,
		editIndex: -1,
		destroyMsg: {
			norecord: {
				title: '提示信息',
				msg: '请选择要删除的用户.'
			},
			confirm: {
				title: '提示信息',
				msg: '你确定要删除该用户吗?'
			}
		},
		//		destroyConfirmTitle: 'Confirm',
		//		destroyConfirmMsg: 'Are you sure you want to delete?',

		autoSave: false, // auto save the editing row when click out of datagrid
		url: null, // return the datagrid data
		saveUrl: null, // return the added row
		updateUrl: null, // return the updated row
		destroyUrl: null, // return {success:true}

		tree: null, // the tree selector
		treeUrl: null, // return tree data
		treeDndUrl: null, // to process the drag and drop operation, return {success:true}
		treeTextField: 'name',
		treeParentField: 'parentId',

		onAdd: function(index, row) {},
		onEdit: function(index, row) {},
		onBeforeSave: function(index) {},
		onSave: function(index, row) {},
		onDestroy: function(index, row) {},
		onError: function(index, row) {}
	});
})(jQuery);