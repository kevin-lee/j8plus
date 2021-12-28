"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[39],{3905:function(e,t,n){n.d(t,{Zo:function(){return p},kt:function(){return d}});var r=n(7294);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function u(e,t){if(null==e)return{};var n,r,i=function(e,t){if(null==e)return{};var n,r,i={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(i[n]=e[n]);return i}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(i[n]=e[n])}return i}var l=r.createContext({}),c=function(e){var t=r.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},p=function(e){var t=c(e.components);return r.createElement(l.Provider,{value:t},e.children)},h={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},s=r.forwardRef((function(e,t){var n=e.components,i=e.mdxType,o=e.originalType,l=e.parentName,p=u(e,["components","mdxType","originalType","parentName"]),s=c(n),d=i,m=s["".concat(l,".").concat(d)]||s[d]||h[d]||o;return n?r.createElement(m,a(a({ref:t},p),{},{components:n})):r.createElement(m,a({ref:t},p))}));function d(e,t){var n=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var o=n.length,a=new Array(o);a[0]=s;var u={};for(var l in t)hasOwnProperty.call(t,l)&&(u[l]=t[l]);u.originalType=e,u.mdxType="string"==typeof e?e:i,a[1]=u;for(var c=2;c<o;c++)a[c]=n[c];return r.createElement.apply(null,a)}return r.createElement.apply(null,n)}s.displayName="MDXCreateElement"},935:function(e,t,n){n.r(t),n.d(t,{frontMatter:function(){return u},contentTitle:function(){return l},metadata:function(){return c},toc:function(){return p},default:function(){return s}});var r=n(7462),i=n(3366),o=(n(7294),n(3905)),a=["components"],u={id:"either",title:"Either - Turn Your Partial Function into a Total Function",sidebar_label:"Either (WIP)"},l=void 0,c={unversionedId:"types/either",id:"types/either",isDocsHomePage:!1,title:"Either - Turn Your Partial Function into a Total Function",description:"Why Either",source:"@site/docs/types/either.md",sourceDirName:"types",slug:"/types/either",permalink:"/docs/types/either",tags:[],version:"current",frontMatter:{id:"either",title:"Either - Turn Your Partial Function into a Total Function",sidebar_label:"Either (WIP)"},sidebar:"j8PlusSidebar",previous:{title:"Maybe",permalink:"/docs/types/maybe"},next:{title:"Runner (WIP)",permalink:"/docs/types/runner"}},p=[{value:"Why <code>Either</code>",id:"why-either",children:[]},{value:"Create",id:"create",children:[{value:"Either.right to contain the result",id:"eitherright-to-contain-the-result",children:[]},{value:"Either.right to contain the error",id:"eitherright-to-contain-the-error",children:[]},{value:"Either Example",id:"either-example",children:[]}]}],h={toc:p};function s(e){var t=e.components,n=(0,i.Z)(e,a);return(0,o.kt)("wrapper",(0,r.Z)({},h,n,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("h2",{id:"why-either"},"Why ",(0,o.kt)("inlineCode",{parentName:"h2"},"Either")),(0,o.kt)("p",null,(0,o.kt)("inlineCode",{parentName:"p"},"Either")," can turn a partial function into a total function."),(0,o.kt)("p",null,"When your function (or method) is a partial function meaning that it may not have a return value for some inputs, your code becomes hard to understand and maintain because it may throw an ",(0,o.kt)("inlineCode",{parentName:"p"},"Exception")," when there's no corresponding result for a such input. Throwing an ",(0,o.kt)("inlineCode",{parentName:"p"},"Exception")," means that there might be any number exceptions that can be thrown from the function, and to see what might be thrown, you have to check the implementation details. "),(0,o.kt)("p",null,"To make it a total function so that it always has the output for the given input, there can be multiple ways but an easy and recommended way is using ",(0,o.kt)("inlineCode",{parentName:"p"},"Either"),"."),(0,o.kt)("h2",{id:"create"},"Create"),(0,o.kt)("h3",{id:"eitherright-to-contain-the-result"},"Either.right to contain the result"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-java"},"import j8plus.types.Either;\n\nEither.<String, Integer>right(1);\n// Either<String, Integer> = Either.Right(1)\n")),(0,o.kt)("h3",{id:"eitherright-to-contain-the-error"},"Either.right to contain the error"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-java"},'import j8plus.types.Either;\n\nEither.<String, Integer>left("Error message");\n// Either<String, Integer> = Either.Right(1)\n')),(0,o.kt)("h3",{id:"either-example"},"Either Example"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-java"},'import j8plus.types.Either;\n\npublic class DivisionByZeroError {\n  public final int dividend;\n  \n  public DivisionByZeroError(final int dividend) {\n    this.dividend = dividend;\n  }\n  \n  public String getMessage() {\n    return "Arithmetic error: dividing " + dividend + " by zero is not possible.";\n  }\n\n  @Override\n  public String toString() {\n    return new StringBuilder("DivisionByZeroError(")\n      .append("dividend=").append(dividend)\n      .append(\')\')\n      .toString();\n  }\n}\n\npublic Either<DivisionByZeroError, Integer> divide(int a, int b) {\n  if (b == 0) {\n    return Either.left(new DivisionByZeroError(a));\n  } else {\n    return Either.right(a / b);\n  }\n}\n\nfinal var result1 = divide(10, 2);\n// Either<DivisionByZeroError, Integer> = Right(5)\n\nfinal var result2 = divide(10, 0);\n// Either<DivisionByZeroError, Integer> = Left(DivisionByZeroError(dividend=10))\n')))}s.isMDXComponent=!0}}]);