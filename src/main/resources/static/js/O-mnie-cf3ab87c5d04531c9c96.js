(window.webpackJsonp=window.webpackJsonp||[]).push([[6],{43:function(t,n,e){"use strict";const i={data:()=>({content:null}),methods:{fetchData(t){return t&&"string"==typeof t?this.$http({method:"get",url:t,headers:{"Content-Type":"application/json"}}).then(({data:t})=>{this.content=t}):Promise.reject("invalid url")}}};n.a=i},65:function(t,n,e){"use strict";e.r(n);var i=function(){var t=this,n=t.$createElement,e=t._self._c||n;return t.content?e("main",[e("header",[t._v("O MNIE")]),t._v(" "),e("p",[t._v(t._s(t.content.banner))]),t._v(" "),e("p",{staticClass:"mr-auto",domProps:{innerHTML:t._s(t.content.banner.quote)}},[t._v(t._s(t.content.banner.quote))]),t._v(" "),e("v-img",{attrs:{src:t.content.banner.path}}),t._v(" "),e("p",[t._v(t._s(t.content.description))])],1):t._e()};i._withStripped=!0;var a={title:"O mnie",mixins:[e(43).a],mounted(){this.fetchData("/api/omnie")}},o=e(0),s=Object(o.a)(a,i,[],!1,null,null,null);s.options.__file="client/src/views/O-mnie.vue";n.default=s.exports}}]);