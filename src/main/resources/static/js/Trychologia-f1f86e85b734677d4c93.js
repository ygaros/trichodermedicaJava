(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{43:function(t,e,n){"use strict";const i={data:()=>({content:null}),methods:{fetchData(t){return t&&"string"==typeof t?this.$http({method:"get",url:t,headers:{"Content-Type":"application/json"}}).then(({data:t})=>{this.content=t}):Promise.reject("invalid url")}}};e.a=i},66:function(t,e,n){"use strict";n.r(e);var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return t.content?n("main",[n("header",[n("h3",[t._v(t._s(t.content.zabieg.name))])]),t._v(" "),n("ul",[t._l(t.content.zabieg.procedures,(function(e,i){return n("li",{key:i},[n("p",[t._v("\n        "+t._s(e.name)+"\n      ")])])})),t._v(" "),n("p",[t._v("Czas trwania: "+t._s(t.content.zabieg.duration))])],2)]):t._e()};i._withStripped=!0;var a={mixins:[n(43).a],computed:{title(){return this.content?this.content.zabieg.name:"Trychologia"},id(){return!!this.$route.params.id&&this.$route.params.id}},created(){this.id?this.fetchData(`/api/trychologia/${this.id}`).then(this.changeTitle).catch(t=>this.$router.push(-1)):this.$router.push("/trychologia/1")},beforeRouteUpdate(t,e,n){this.fetchData(`/api/trychologia/${t.params.id}`).then(this.changeTitle).then(n).catch(t=>{n(!1)})}},r=n(0),o=Object(r.a)(a,i,[],!1,null,null,null);o.options.__file="client/src/views/Trychologia.vue";e.default=o.exports}}]);