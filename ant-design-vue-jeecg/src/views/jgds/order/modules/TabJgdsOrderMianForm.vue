<template>
<a-spin :spinning="confirmLoading">

   <a-tabs v-model="activeKey" @change="handleChangeTabs">
   <!--主表区域 -->
    <a-tab-pane tab="Tab商品订单" :key="refKeys[0]" :forceRender="true">
         <a-form-model ref="form" :model="model" :rules="validatorRules">
           <a-row>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="订单编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="orderCode">
                      <a-input v-model="model.orderCode" placeholder="请输入订单编号" ></a-input>
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="下单时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="xdTime">
                      <j-date placeholder="请选择下单时间" v-model="model.xdTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="订单总额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="money">
                      <a-input-number v-model="model.money" placeholder="请输入订单总额" style="width: 100%" />
                    </a-form-model-item>
                  </a-col>
                  <a-col :xs="24" :sm="12">
                    <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remark">
                      <a-input v-model="model.remark" placeholder="请输入备注" ></a-input>
                    </a-form-model-item>
                  </a-col>

                </a-row>
              </a-form-model>

     </a-tab-pane>
   <!--子表单区域 -->
     <a-tab-pane tab="Tab订单商品" :key="refKeys[1]" :forceRender="true">
       <j-editable-table
         :ref="refKeys[1]"
         :loading="tabJgdsOrderGoodsTable.loading"
         :columns="tabJgdsOrderGoodsTable.columns"
         :dataSource="tabJgdsOrderGoodsTable.dataSource"
         :maxHeight="300"
         :rowNumber="true"
         :rowSelection="true"
         :actionButton="true"/>
     </a-tab-pane>

     <a-tab-pane tab="Tab订单客户" :key="refKeys[2]" :forceRender="true">
       <j-editable-table
         :ref="refKeys[2]"
         :loading="tabJgdsOrderCustomTable.loading"
         :columns="tabJgdsOrderCustomTable.columns"
         :dataSource="tabJgdsOrderCustomTable.dataSource"
         :maxHeight="300"
         :rowNumber="true"
         :rowSelection="true"
         :actionButton="true"/>
     </a-tab-pane>

   </a-tabs>

 </a-spin>
</j-modal>
</template>

<script>

import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
import { JEditableTableModelMixin } from '@/mixins/JEditableTableModelMixin'
import { validateDuplicateValue } from '@/utils/util'
import { VALIDATE_NO_PASSED, validateFormModelAndTables } from '@/utils/JEditableTableUtil'

export default {
 name: 'TabJgdsOrderMianForml',
 mixins: [JEditableTableModelMixin],
 components: {
 },
 data() {
   return {
     labelCol: {
       xs: { span: 24 },
       sm: { span: 6 },
     },
     wrapperCol: {
       xs: { span: 24 },
       sm: { span: 16 },
     },
     labelCol2: {
       xs: { span: 24 },
       sm: { span: 3 },
     },
     wrapperCol2: {
       xs: { span: 24 },
       sm: { span: 20 },
     },
     // 新增时子表默认添加几行空数据
     addDefaultRowNum: 1,
     model:{
     },
        validatorRules: {
        },
     refKeys: ['tabJgdsOrderMian','tabJgdsOrderGoods', 'tabJgdsOrderCustom', ],
     tableKeys:['tabJgdsOrderGoods', 'tabJgdsOrderCustom', ],
     activeKey: 'tabJgdsOrderMian',
     // Tab订单商品
     tabJgdsOrderGoodsTable: {
       loading: false,
       dataSource: [],
       columns: [
         {
           title: '订单商品名字',
           key: 'goodName',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '价格',
           key: 'price',
           type: FormTypes.inputNumber,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '数量',
           key: 'num',
           type: FormTypes.inputNumber,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '单品总价',
           key: 'sumPrice',
           type: FormTypes.inputNumber,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
       ]
     },
     // Tab订单客户
     tabJgdsOrderCustomTable: {
       loading: false,
       dataSource: [],
       columns: [
         {
           title: '客户名称',
           key: 'name',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '性别',
           key: 'sex',
           type: FormTypes.select,
           dictCode:"sex",
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '客户生日',
           key: 'birthday',
           type: FormTypes.date,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '年龄',
           key: 'age',
           type: FormTypes.inputNumber,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
         {
           title: '常用地址',
           key: 'address',
           type: FormTypes.input,
           width:"200px",
           placeholder: '请输入${title}',
           defaultValue:'',
         },
       ]
     },
     url: {
       add: "/order/tabJgdsOrderMian/add",
       edit: "/order/tabJgdsOrderMian/edit",
        tabJgdsOrderMian: {
         list: '/order/tabJgdsOrderMian/queryById'
        },
       tabJgdsOrderGoods: {
         list: '/order/tabJgdsOrderMian/queryTabJgdsOrderGoodsByMainId'
       },
       tabJgdsOrderCustom: {
         list: '/order/tabJgdsOrderMian/queryTabJgdsOrderCustomByMainId'
       },
     }
   }
 },
 methods: {
   getAllTable() {
     let values = this.tableKeys.map(key => getRefPromise(this, key))
     return Promise.all(values)
   },
   /** 调用完edit()方法之后会自动调用此方法 */
   editAfter() {
     this.$nextTick(() => {
     })
     // 加载子表数据
     if (this.model.id) {
       let params = { id: this.model.id }
       this.requestSubTableData(this.url.tabJgdsOrderGoods.list, params, this.tabJgdsOrderGoodsTable)
       this.requestSubTableData(this.url.tabJgdsOrderCustom.list, params, this.tabJgdsOrderCustomTable)
     }
   },
   //校验所有一对一子表表单
   validateSubForm(allValues){
     return new Promise((resolve,reject)=>{
       Promise.all([
       ]).then(() => {
         resolve(allValues)
       }).catch(e => {
         reject(e)
       })
     })
   },
   /** 整理成formData */
   classifyIntoFormData(allValues) {
     let main = Object.assign(this.model, allValues.formValue)
     return {
       ...main, // 展开
       tabJgdsOrderGoodsList: allValues.tablesValue[0].values,
       tabJgdsOrderCustomList: allValues.tablesValue[1].values,
     }
   },
    /** 确定按钮点击事件 */
     handleOk() {
       /** 触发表单验证 */
       this.getAllTable().then(tables => {
          return validateFormModelAndTables(this.$refs.form,this.model, tables)
       }).then(allValues => {
         /** 一次性验证一对一的所有子表 */
         return this.validateSubForm(allValues)
       }).then(allValues => {
         if (typeof this.classifyIntoFormData !== 'function') {
           throw this.throwNotFunction('classifyIntoFormData')
         }
         console.log("this.classifyIntoFormData",typeof this.classifyIntoFormData)
         let formData = this.classifyIntoFormData(allValues)

         // 发起请求
         return this.request(formData)
       }).catch(e => {
         if (e.error === VALIDATE_NO_PASSED) {
           // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
           this.activeKey = e.index == null ? this.refKeys[0] : this.refKeys[e.index+1]
         } else {
           console.error(e)
         }
       })
     },
   validateError(msg){
     this.$message.error(msg)
   },
 close() {
   this.visible = false
   this.$emit('close')
   this.$refs.form.clearValidate();
 },

 }
}
</script>

<style scoped>
</style>