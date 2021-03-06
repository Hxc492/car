<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>客户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="姓名" id="name">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="手机号" id="phone">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">地址</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" placeholder="地址" id="address">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" id="searchBtn" type="button">搜索</button>
                    <button class="layui-btn layui-btn-primary" type="reset">重置</button>
                </div>
            </div>
        </form>
    </blockquote>
    <table id="dataTable" lay-filter="dataTableFilter"></table>
    <!--操作-->
    <script type="text/html" id="headBtns">
        <shiro:hasPermission name="customer:add">
            <button class="layui-btn layui-btn-sm" lay-event="add">
                <i class="layui-icon layui-icon-add-1"></i>
                新增
            </button>
        </shiro:hasPermission>
        <shiro:hasPermission name="customer:export">
            <button class="layui-btn layui-btn-sm" lay-event="export">
                <i class="layui-icon layui-icon-release"></i>
                导出
            </button>
        </shiro:hasPermission>
        <shiro:hasPermission name="customer:import">
            <button class="layui-btn layui-btn-sm" lay-event="import">
                <i class="layui-icon layui-icon-upload"></i>
                导入
            </button>
        </shiro:hasPermission>
    </script>
    <script type="text/html" id="rowBtns">
        <shiro:hasPermission name="customer:update">
            <button class="layui-btn layui-btn-sm" lay-event="edit">
                <i class="layui-icon layui-icon-edit"></i>
                修改
            </button>
        </shiro:hasPermission>
    </script>
</form>
<%-- 编辑客户的模板 --%>
<script type="text/html" id="editTpl">
    <form class="layui-form layui-form-pane" style="margin: 10px" lay-filter="editFormFilter">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required" lay-reqText="请输入姓名" placeholder="客户姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" lay-verify="required|phone" lay-reqText="请输入手机号" placeholder="手机号" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">身份证</label>
                <div class="layui-input-inline">
                    <input type="text" name="idCard" lay-verify="required|idCard" lay-reqText="请输入身份证" placeholder="身份证" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="2" title="女">
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="address" placeholder="地址" lay-reqText="请输入地址" lay-verify="required"></textarea>
            </div>
        </div>
        <button type="button" style="display: none" id="subBtn" lay-submit lay-filter="subBtnFilter"></button>
    </form>
</script>
<%-- 上传文件模板 --%>
<script type="text/html" id="uploadTpl">
    <form class="layui-form layui-form-pane" style="padding-left: 70px; padding-top:30px ">
        <div class="layui-form-item">
            <label class="layui-form-label">客户文件</label>
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-normal" id="chooseBtn">选择文件</button>
                <button type="button" class="layui-btn" id="uploadBtn">上传</button>
            </div>
        </div>
    </form>
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table', 'jquery', 'layer', 'form', 'upload'], function () {
        let cxt = '${pageContext.request.contextPath}';
        let table = layui.table;
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        let upload = layui.upload;
        //渲染数据表格
        //表格参数
        let tabOps = {
            id: "dataTableId",
            elem: "#dataTable",
            url: cxt + "/customer/page.do",
            page: true,//开启分页
            toolbar: "#headBtns",//头工具栏
            cols: [[
                {type: "checkbox"},
                {field: "name", align: "center", title: "姓名"},
                {field: "phone", align: "center", title: "电话"},
                {field: "idCard", align: "center", title: "身份证", minWidth: 160},
                {
                    field: "sex", align: "center", title: "性别", width: 80, templet: function (d) {
                        let sex = d.sex;
                        if (sex == 1) {
                            return "<p style='color: #1385e8'>男</p>";
                        } else if (sex == 2) {
                            return "<p style='color: #e8139a'>女</p>";
                        } else {
                            return "<p style='color: #e51111'>不详</p>";
                        }
                    }
                },
                {field: "address", align: "center", title: "地址"},
                {field: "createTime", align: "center", title: "创建时间", width: 120},
                {field: "updateTime", align: "center", title: "修改时间", width: 120},
                {title: "操作", align: "center", toolbar: "#rowBtns", minWidth: 230, fixed: "right"}
            ]],//列数据
            parseData: function (rs) {
                return {
                    code: rs.code,
                    msg: rs.msg,
                    count: rs.data.total,
                    data: rs.data.list
                }
            },
            response: {
                statusCode: 200
            }
        };
        //进行渲染
        let tabIns = table.render(tabOps);
        //按钮查询
        $("#searchBtn").click(function () {
            let name = $("#name").val();
            let phone = $("#phone").val();
            let address = $("#address").val();
            tabIns.reload({
                where: {
                    "name": name,
                    "phone": phone,
                    "address": address
                }
            })
        });

        //表格头工具栏监听事件
        table.on("toolbar(dataTableFilter)", function (d) {
            let event = d.event;
            if (event == "add") {
                add();
            } else if (event == "export") {
                customerExport();
            } else if (event == "import") {
                customerImport();
            }
        })

        //弹出层的参数
        let layOps = {
            title: "编辑客户",
            skin: "layui-layer-molv",
            type: 1,
            content: $("#editTpl").html(),
            area: ['425px', '530px'],
            success: function (layero, index) {
                form.render("radio");
                //表单的提交监听
                form.on("submit(subBtnFilter)", function (d) {
                    let formData = d.field;
                    //使用ajax提交数据
                    $.post(cxt + "/customer/add.do", formData, function (rs) {
                        layer.msg(rs.msg);//展示业务消息
                        if (rs.code != 200) {
                            return false;
                        }
                        layer.close(index);//关闭弹层
                        //刷新表格
                        $("#searchBtn").click();
                    })
                    return false;//阻止默认提交行为
                })
            },
            btn: ['确认', '取消'],
            btnAlign: "c",
            yes: function (index, layero) {
                //点击隐藏的提交按钮  触发 表单提交监听
                $("#subBtn").click();
            }
        };

        /**
         * 新增客户的方法
         */
        function add() {
            layer.open(layOps);
        }

        /**
         * 导出客户
         */
        function customerExport() {
            let name = $("#name").val();
            let phone = $("#phone").val();
            let address = $("#address").val();
            //请求文件导出
            location.href = cxt + "/customer/export.do?name=" + name + "&phone=" + phone + "&address=" + address;
        }

        /**
         * 导入客户数据
         */
        function customerImport() {
            let opt = {
                title: "上传文件",
                skin: "layui-layer-lan",
                type: 1,
                content: $("#uploadTpl").html(),
                area: ['470px', '140px'],
                success: function (layero, index) {
                    console.log("文件导入的弹出层已经弹出")
                    //初始化文件上传
                    let uploadOpt = {
                        elem: "#chooseBtn",
                        url: cxt + "/customer/import.do",//处理文件上传的接口
                        auto: false,//不自动上传
                        bindAction: "#uploadBtn",//绑定具体上传的按钮
                        field: "customers",//指定文件上传的数据域名称
                        accept: "file",//允许上传文件
                        done: function (rs, fileIndex, upload) {
                            //将业务消息展示
                            layer.msg(rs.msg);
                            if (rs.code == 200) {
                                //关闭弹出层
                                layer.close(index);
                                $("#searchBtn").click();//重载表格
                            }

                        }

                    };
                    upload.render(uploadOpt);
                }
            }
            layer.open(opt)
        }


        //行工具栏监听事件
        table.on("tool(dataTableFilter)", function (d) {
            let event = d.event;
            let rowData = d.data;
            if (event == "edit") {
                edit(rowData);//修改客户
            }
        });

        //具体的修改方法
        function edit(rowData) {
            //将对象复制一份
            let ops = new Object();//目标对象
            Object.assign(ops, layOps);//将layOps 对象复制给 ops 对象
            ops.success = function (layero, index) {
                form.render("radio");
                form.val("editFormFilter", rowData); // 进行数据回显
                //表单的提交监听
                form.on("submit(subBtnFilter)", function (d) {
                    let formData = d.field;
                    formData.id = rowData.id;//获取ID
                    //使用ajax提交数据
                    $.post(cxt + "/customer/update.do", formData, function (rs) {
                        layer.msg(rs.msg);//展示业务消息
                        if (rs.code != 200) {
                            return false;
                        }
                        layer.close(index);//关闭弹层
                        //刷新表格
                        $("#searchBtn").click();
                    })
                    return false;//阻止默认提交行为
                })
            }
            layer.open(ops);
        }

    })
</script>
</body>
</html>