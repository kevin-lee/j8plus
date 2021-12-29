"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[39],{3905:function(e,t,n){n.d(t,{Zo:function(){return c},kt:function(){return s}});var r=n(7294);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function o(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,r,i=function(e,t){if(null==e)return{};var n,r,i={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(i[n]=e[n]);return i}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(i[n]=e[n])}return i}var h=r.createContext({}),d=function(e){var t=r.useContext(h),n=t;return e&&(n="function"==typeof e?e(t):o(o({},t),e)),n},c=function(e){var t=d(e.components);return r.createElement(h.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},u=r.forwardRef((function(e,t){var n=e.components,i=e.mdxType,a=e.originalType,h=e.parentName,c=l(e,["components","mdxType","originalType","parentName"]),u=d(n),s=i,m=u["".concat(h,".").concat(s)]||u[s]||p[s]||a;return n?r.createElement(m,o(o({ref:t},c),{},{components:n})):r.createElement(m,o({ref:t},c))}));function s(e,t){var n=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var a=n.length,o=new Array(a);o[0]=u;var l={};for(var h in t)hasOwnProperty.call(t,h)&&(l[h]=t[h]);l.originalType=e,l.mdxType="string"==typeof e?e:i,o[1]=l;for(var d=2;d<a;d++)o[d]=n[d];return r.createElement.apply(null,o)}return r.createElement.apply(null,n)}u.displayName="MDXCreateElement"},935:function(e,t,n){n.r(t),n.d(t,{frontMatter:function(){return l},contentTitle:function(){return h},metadata:function(){return d},toc:function(){return c},default:function(){return u}});var r=n(7462),i=n(3366),a=(n(7294),n(3905)),o=["components"],l={id:"either",title:"Either - Turn Your Partial Function into a Total Function",sidebar_label:"Either (WIP)"},h=void 0,d={unversionedId:"types/either",id:"types/either",isDocsHomePage:!1,title:"Either - Turn Your Partial Function into a Total Function",description:"Why Either",source:"@site/docs/types/either.md",sourceDirName:"types",slug:"/types/either",permalink:"/docs/types/either",tags:[],version:"current",frontMatter:{id:"either",title:"Either - Turn Your Partial Function into a Total Function",sidebar_label:"Either (WIP)"},sidebar:"j8PlusSidebar",previous:{title:"Maybe",permalink:"/docs/types/maybe"},next:{title:"Runner (WIP)",permalink:"/docs/types/runner"}},c=[{value:"Why <code>Either</code>",id:"why-either",children:[]},{value:"Create",id:"create",children:[{value:"Either.right to contain the result",id:"eitherright-to-contain-the-result",children:[]},{value:"Either.right to contain the error",id:"eitherright-to-contain-the-error",children:[]},{value:"<code>Either</code> from <code>Optional</code>",id:"either-from-optional",children:[]},{value:"<code>Either</code> from <code>Maybe</code>",id:"either-from-maybe",children:[]}]},{value:"Transform",id:"transform",children:[{value:"<code>Either.map</code>",id:"eithermap",children:[]},{value:"<code>Either.flatMap</code>",id:"eitherflatmap",children:[]},{value:"<code>Either.swap</code>",id:"eitherswap",children:[]}]},{value:"Get the Value",id:"get-the-value",children:[{value:"<code>Either.fold</code>",id:"eitherfold",children:[]}]},{value:"Check Value in Either",id:"check-value-in-either",children:[{value:"<code>Either.isRight</code>",id:"eitherisright",children:[]},{value:"<code>Either.isLeft</code>",id:"eitherisleft",children:[]},{value:"Example",id:"example",children:[]}]}],p={toc:c};function u(e){var t=e.components,n=(0,i.Z)(e,o);return(0,a.kt)("wrapper",(0,r.Z)({},p,n,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h2",{id:"why-either"},"Why ",(0,a.kt)("inlineCode",{parentName:"h2"},"Either")),(0,a.kt)("p",null,(0,a.kt)("inlineCode",{parentName:"p"},"Either")," can turn a partial function into a total function."),(0,a.kt)("p",null,"When your function (or method) is a partial function meaning that it may not have a return value for some inputs, your code becomes hard to understand and maintain because it may throw an ",(0,a.kt)("inlineCode",{parentName:"p"},"Exception")," when there's no corresponding result for a such input. Throwing an ",(0,a.kt)("inlineCode",{parentName:"p"},"Exception")," means that there might be any number exceptions that can be thrown from the function, and to see what might be thrown, you have to check the implementation details. "),(0,a.kt)("p",null,"To make it a total function so that it always has the output for the given input, there can be multiple ways but an easy and recommended way is using ",(0,a.kt)("inlineCode",{parentName:"p"},"Either"),"."),(0,a.kt)("div",{className:"admonition admonition-caution alert alert--warning"},(0,a.kt)("div",{parentName:"div",className:"admonition-heading"},(0,a.kt)("h5",{parentName:"div"},(0,a.kt)("span",{parentName:"h5",className:"admonition-icon"},(0,a.kt)("svg",{parentName:"span",xmlns:"http://www.w3.org/2000/svg",width:"16",height:"16",viewBox:"0 0 16 16"},(0,a.kt)("path",{parentName:"svg",fillRule:"evenodd",d:"M8.893 1.5c-.183-.31-.52-.5-.887-.5s-.703.19-.886.5L.138 13.499a.98.98 0 0 0 0 1.001c.193.31.53.501.886.501h13.964c.367 0 .704-.19.877-.5a1.03 1.03 0 0 0 .01-1.002L8.893 1.5zm.133 11.497H6.987v-2.003h2.039v2.003zm0-3.004H6.987V5.987h2.039v4.006z"}))),"NOTE")),(0,a.kt)("div",{parentName:"div",className:"admonition-content"},(0,a.kt)("p",{parentName:"div"},(0,a.kt)("inlineCode",{parentName:"p"},"Either")," is right-biased meaning that the most well-known combinators on ",(0,a.kt)("inlineCode",{parentName:"p"},"Either")," (e.g. ",(0,a.kt)("inlineCode",{parentName:"p"},"map"),", ",(0,a.kt)("inlineCode",{parentName:"p"},"flatMap"),", etc.) are by default for the ",(0,a.kt)("inlineCode",{parentName:"p"},"Right")," value. If you want to manipulate the left one, you need to use the combinators for ",(0,a.kt)("inlineCode",{parentName:"p"},"Left")," which are usually prefixed with ",(0,a.kt)("inlineCode",{parentName:"p"},"left")," (e.g. ",(0,a.kt)("inlineCode",{parentName:"p"},"leftMap"),", ",(0,a.kt)("inlineCode",{parentName:"p"},"leftFlatMap"),", etc.).  "))),(0,a.kt)("h2",{id:"create"},"Create"),(0,a.kt)("h3",{id:"eitherright-to-contain-the-result"},"Either.right to contain the result"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-java"},"import j8plus.types.Either;\n\nEither.<String, Integer>right(1);\n// Either<String, Integer> = Either.Right(1)\n")),(0,a.kt)("h3",{id:"eitherright-to-contain-the-error"},"Either.right to contain the error"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-java"},'import j8plus.types.Either;\n\nEither.<String, Integer>left("Error message");\n// Either<String, Integer> = Either.Right(1)\n')),(0,a.kt)("h3",{id:"either-from-optional"},(0,a.kt)("inlineCode",{parentName:"h3"},"Either")," from ",(0,a.kt)("inlineCode",{parentName:"h3"},"Optional")),(0,a.kt)("h3",{id:"either-from-maybe"},(0,a.kt)("inlineCode",{parentName:"h3"},"Either")," from ",(0,a.kt)("inlineCode",{parentName:"h3"},"Maybe")),(0,a.kt)("h2",{id:"transform"},"Transform"),(0,a.kt)("h3",{id:"eithermap"},(0,a.kt)("inlineCode",{parentName:"h3"},"Either.map")),(0,a.kt)("h3",{id:"eitherflatmap"},(0,a.kt)("inlineCode",{parentName:"h3"},"Either.flatMap")),(0,a.kt)("h3",{id:"eitherswap"},(0,a.kt)("inlineCode",{parentName:"h3"},"Either.swap")),(0,a.kt)("h2",{id:"get-the-value"},"Get the Value"),(0,a.kt)("h3",{id:"eitherfold"},(0,a.kt)("inlineCode",{parentName:"h3"},"Either.fold")),(0,a.kt)("h2",{id:"check-value-in-either"},"Check Value in Either"),(0,a.kt)("h3",{id:"eitherisright"},(0,a.kt)("inlineCode",{parentName:"h3"},"Either.isRight")),(0,a.kt)("h3",{id:"eitherisleft"},(0,a.kt)("inlineCode",{parentName:"h3"},"Either.isLeft")),(0,a.kt)("h3",{id:"example"},"Example"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-java"},'import j8plus.types.Either;\n\npublic class DivisionByZeroError {\n  public final int dividend;\n  \n  public DivisionByZeroError(final int dividend) {\n    this.dividend = dividend;\n  }\n  \n  public String getMessage() {\n    return "Arithmetic error: dividing " + dividend + " by zero is not possible.";\n  }\n\n  @Override\n  public String toString() {\n    return new StringBuilder("DivisionByZeroError(")\n      .append("dividend=").append(dividend)\n      .append(\')\')\n      .toString();\n  }\n}\n\npublic Either<DivisionByZeroError, Integer> divide(int a, int b) {\n  if (b == 0) {\n    return Either.left(new DivisionByZeroError(a));\n  } else {\n    return Either.right(a / b);\n  }\n}\n\nfinal var result1 = divide(10, 2);\n// Either<DivisionByZeroError, Integer> = Right(5)\n\nfinal var result2 = divide(10, 0);\n// Either<DivisionByZeroError, Integer> = Left(DivisionByZeroError(dividend=10))\n')))}u.isMDXComponent=!0}}]);