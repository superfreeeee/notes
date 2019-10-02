# Vue源碼目錄
```bash
src
├── compiler/
│   ├── codeframe.js
│   ├── codegen/
│   │   ├── events.js
│   │   └── index.js
│   ├── create-compiler.js
│   ├── directives/
│   │   ├── bind.js
│   │   ├── index.js
│   │   ├── model.js
│   │   └── on.js
│   ├── error-detector.js
│   ├── helpers.js
│   ├── index.js
│   ├── optimizer.js
│   ├── parser/
│   │   ├── entity-decoder.js
│   │   ├── filter-parser.js
│   │   ├── html-parser.js
│   │   ├── index.js
│   │   └── text-parser.js
│   └── to-function.js
├── core/
│   ├── components/
│   │   ├── index.js
│   │   └── keep-alive.js
│   ├── config.js
│   ├── global-api/
│   │   ├── assets.js
│   │   ├── extend.js
│   │   ├── index.js
│   │   ├── mixin.js
│   │   └── use.js
│   ├── index.js
│   ├── instance/
│   │   ├── events.js
│   │   ├── index.js    // Vue原始定義，初始化
│   │   │   > export default Vue
│   │   ├── init.js     // 初始化vm._init
│   │   │   > export function initMixin (Vue)
│   │   │     > define vm._init
│   │   │       > set vm._uid
│   │   │       > set vm._isVue
│   │   │       > set vm._renderProxy
│   │   │       > set vm._self
│   │   │       > set vm._name
│   │   │       > set vm.$options
│   │   │       > use function initInternalComponent
│   │   │       > use function resolveConstructorOptions
│   │   │   > export function initInternalComponent(vm, options)
│   │   │   > export function resolveConstructorOptions(Ctor)
│   │   │     > use function resolveModifiedOptions(Ctor)
│   │   ├── inject.js
│   │   ├── lifecycle.js
│   │   │   > export function mountComponent(vm, el, hydrating)   // 掛載實例
│   │   │     > create new Watcher
│   │   │       > use vm._render
│   │   │       > use vm._update
│   │   │     > set vm._isMounted
│   │   │   > export function lifecycleMixin(Vue)
│   │   │     > define vm._update
│   │   │       > use vm.__patch__ 
│   │   ├── proxy.js
│   │   ├── render-helpers/
│   │   │   ├── bind-dynamic-keys.js
│   │   │   ├── bind-object-listeners.js
│   │   │   ├── bind-object-props.js
│   │   │   ├── check-keycodes.js
│   │   │   ├── index.js
│   │   │   ├── render-list.js
│   │   │   ├── render-slot.js
│   │   │   ├── render-static.js
│   │   │   ├── resolve-filter.js
│   │   │   ├── resolve-scoped-slots.js
│   │   │   └── resolve-slots.js
│   │   ├── render.js
│   │   │   > export function renderMixin(Vue)
│   │   │     > define vm._render 
│   │   │       > use vm.$createElement
│   │   └── state.js
│   ├── observer/
│   │   ├── array.js
│   │   ├── dep.js
│   │   ├── index.js
│   │   ├── scheduler.js
│   │   ├── traverse.js
│   │   └── watcher.js
│   ├── util/
│   │   ├── debug.js
│   │   ├── env.js
│   │   ├── error.js
│   │   ├── index.js     // export目錄下所有模塊
│   │   ├── lang.js
│   │   ├── next-tick.js
│   │   ├── options.js
│   │   ├── perf.js
│   │   └── props.js
│   └── vdom/
│       ├── create-component.js
│       │   > export function createComponent(Ctor, data, context, children, tag)
│       ├── create-element.js
│       │   > export function createElement
│       │     > decorate function _createElement(context, tag, data, children, normalizationType)
│       │       > use function normalizeChildren 
│       │       > use function simpleNormalizeChildren 
│       ├── create-functional-component.js
│       ├── helpers/
│       │   ├── extract-props.js
│       │   ├── get-first-component-child.js
│       │   ├── index.js
│       │   ├── is-async-placeholder.js
│       │   ├── merge-hook.js
│       │   ├── normalize-children.js
│       │   │   > export function simpleNormalizeChildren(children)
│       │   │   > export function normalizeChildren(children)
│       │   │     > use function normalizeArrayChildren(children, nestedIndex)
│       │   ├── normalize-scoped-slots.js
│       │   ├── resolve-async-component.js
│       │   └── update-listeners.js
│       ├── modules/
│       │   ├── directives.js
│       │   ├── index.js
│       │   └── ref.js
│       ├── patch.js
│       │   > export function createPatchFunction(backend)
│       │     > return function patch(oldVnode, vnode, hydrating, removeOnly)
│       └── vnode.js
│           > export default class VNode
├── platforms/
│   ├── web/
│   │   ├── compiler/
│   │   │   ├── directives/
│   │   │   │   ├── html.js
│   │   │   │   ├── index.js
│   │   │   │   ├── model.js
│   │   │   │   └── text.js
│   │   │   ├── index.js
│   │   │   ├── modules/
│   │   │   │   ├── class.js
│   │   │   │   ├── index.js
│   │   │   │   ├── model.js
│   │   │   │   └── style.js
│   │   │   ├── options.js
│   │   │   └── util.js
│   │   ├── entry-compiler.js
│   │   ├── entry-runtime-with-compiler.js      
│   │   │   > decorate vm.$mount     // 替換掛載機制
│   │   ├── entry-runtime.js
│   │   ├── entry-server-basic-renderer.js
│   │   ├── entry-server-renderer.js
│   │   ├── runtime/
│   │   │   ├── class-util.js
│   │   │   ├── components/
│   │   │   │   ├── index.js
│   │   │   │   ├── transition-group.js
│   │   │   │   └── transition.js
│   │   │   ├── directives/
│   │   │   │   ├── index.js
│   │   │   │   ├── model.js
│   │   │   │   └── show.js
│   │   │   ├── index.js      // 原始掛載邏輯
│   │   │   │   > define vm.$mount
│   │   │   │     > decorate function mountComponent
│   │   │   │   > define vm.__patch__
│   │   │   ├── modules/
│   │   │   │   ├── attrs.js
│   │   │   │   ├── class.js
│   │   │   │   ├── dom-props.js
│   │   │   │   ├── events.js
│   │   │   │   ├── index.js
│   │   │   │   ├── style.js
│   │   │   │   └── transition.js
│   │   │   ├── node-ops.js
│   │   │   ├── patch.js
│   │   │   │   > export const patch
│   │   │   │     > use function createPatchFunction
│   │   │   └── transition-util.js
│   │   ├── server/
│   │   │   ├── compiler.js
│   │   │   ├── directives/
│   │   │   │   ├── index.js
│   │   │   │   ├── model.js
│   │   │   │   └── show.js
│   │   │   ├── modules/
│   │   │   │   ├── attrs.js
│   │   │   │   ├── class.js
│   │   │   │   ├── dom-props.js
│   │   │   │   ├── index.js
│   │   │   │   └── style.js
│   │   │   └── util.js
│   │   └── util/
│   │       ├── attrs.js
│   │       ├── class.js
│   │       ├── compat.js
│   │       ├── element.js
│   │       ├── index.js
│   │       └── style.js
│   └── weex/
│       ├── compiler/
│       │   ├── directives/
│       │   │   ├── index.js*
│       │   │   └── model.js
│       │   ├── index.js
│       │   └── modules/
│       │       ├── append.js
│       │       ├── class.js
│       │       ├── index.js
│       │       ├── props.js
│       │       ├── recycle-list/
│       │       │   ├── component-root.js
│       │       │   ├── component.js
│       │       │   ├── index.js
│       │       │   ├── recycle-list.js
│       │       │   ├── text.js
│       │       │   ├── v-bind.js
│       │       │   ├── v-for.js
│       │       │   ├── v-if.js
│       │       │   ├── v-on.js
│       │       │   └── v-once.js
│       │       └── style.js
│       ├── entry-compiler.js
│       ├── entry-framework.js
│       ├── entry-runtime-factory.js
│       ├── runtime/
│       │   ├── components/
│       │   │   ├── index.js
│       │   │   ├── richtext.js
│       │   │   ├── transition-group.js
│       │   │   └── transition.js
│       │   ├── directives/
│       │   │   └── index.js*
│       │   ├── index.js*
│       │   ├── modules/
│       │   │   ├── attrs.js*
│       │   │   ├── class.js*
│       │   │   ├── events.js*
│       │   │   ├── index.js*
│       │   │   ├── style.js*
│       │   │   └── transition.js
│       │   ├── node-ops.js*
│       │   ├── patch.js
│       │   ├── recycle-list/
│       │   │   ├── render-component-template.js
│       │   │   └── virtual-component.js
│       │   └── text-node.js
│       └── util/
│           ├── element.js
│           ├── index.js*
│           └── parser.js
├── server/
│   ├── bundle-renderer/
│   │   ├── create-bundle-renderer.js
│   │   ├── create-bundle-runner.js
│   │   └── source-map-support.js
│   ├── create-basic-renderer.js
│   ├── create-renderer.js
│   ├── optimizing-compiler/
│   │   ├── codegen.js
│   │   ├── index.js
│   │   ├── modules.js
│   │   ├── optimizer.js
│   │   └── runtime-helpers.js
│   ├── render-context.js
│   ├── render-stream.js
│   ├── render.js
│   ├── template-renderer/
│   │   ├── create-async-file-mapper.js
│   │   ├── index.js
│   │   ├── parse-template.js
│   │   └── template-stream.js
│   ├── util.js
│   ├── webpack-plugin/
│   │   ├── client.js
│   │   ├── server.js
│   │   └── util.js
│   └── write.js
├── sfc/
│   └── parser.js
└── shared/
    ├── constants.js
    └── util.js
        > export const emptyObject    // := Object.freeze({})
        > export function isUndef(v)  // := undefine | null ?
        > export function isDef(v)    // := (!undefine) & (!null) ?
        > export function isTrue(v)   // := true ?
        > export function isFalse(v)  // := false ?
        > export function isPrimitive(value)  // := string | number | symbol | boolean ?
        > export function isObject(obj)  // := (!null) & object ?
        > export function toRawType(value)  // get type from [object Object]
        > export function isPlainObject(obj)  // := [object Object] ?
        > export function isRegExp(v)   // := [object RegExp] ?
        > export function isValidArrayIndex(val)  // 大於零的有限整數
        > export function isPromise(val)  // 有定義且有then(), catch()屬性
        > export function toString(val)  // return '' | JSON | String
        > export function toNumber(val)  // return val | number
```
vm === Vue.prototype

# 第一部分：數據驅動  
- DOM總覽  
```
new Vue 
-> init 
-> $mount 
-> compile 
-> render 
-> vnode 
-> patch 
-> DOM
```
- 核心
```
Vue.prototype._init  
Vue.prototype.$mount  
Vue.prototype._render  
Vue.prototype._update  
Vue.prototype.__patch__
```
# 第二部分：組件化
- 