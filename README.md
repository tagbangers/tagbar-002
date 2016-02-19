# TagBar 2

## 1. Hello Hibernate!

1. ネイティブブートストラップ
2. JPA ブートストラップ（persistence.xml あり）
3. JPA ブートストラップ（persistence.xml なし）
4. Spring JPA のブートストラップ

### 1.1. IntelliJ の JPA サポート

1. JPA コンソール
2. ER ダイアグラム

## 2. エンティティのライフサイクル

![ライフサイクル](http://bp2.blogger.com/_ZAzF_V5Np5c/RbcadyCaxTI/AAAAAAAAAAM/p3xRORPddqs/s1600/hibernate-lc.PNG)

1. エンティティのライフサイクル（ネイティブ版）
2. エンティティのライフサイクル（JPA 版）

## 3. いろいろな Query

1. HQL
2. JPQL
3. Criteria
4. Querydsl
5. Specification

## 4. フェッチとカスケード

1. LazyInitializeException
1. EntityGraph

## @Converter

## 悲観的ロック・楽観的ロック

## toOne関連におけるoptional=trueの制約

## Multi-Tenancy

## Hibernate ORM Envers

http://hibernate.org/orm/envers/

## Hibernate Search

## 最近のリリースノート

5.0
http://in.relation.to/2015/08/20/hibernate-orm-500-final-release/

* New bootstrap API
* Spatial/GIS support
* Java 8 support
* Expanded AUTO id generation support
* Naming strategy split
* Attribute Converter support
* Better "bulk id table" support
* Transaction management
* Schema Tooling
* Typed Session API
* Improved OSGi support
* Improved bytecode enhancement capabilities
* Work on documentation
	http://hibernate.org/orm/documentation/5.0/

5.1
http://in.relation.to/2016/02/10/hibernate-orm-510-final-release/

* Entity joins (or ad hoc joins)
* load-by-multiple-id API
* CDI integration improvements
* @Embeddables and all null column values
* Envers audit queries can now refer to to-one associtions
		

## Memo

http://vladmihalcea.com/2015/11/26/how-to-bootstrap-hibernate-without-the-persistence-xml-file/

https://3f693e6410e6b3f1505c6d5f747d0a3fc10fd6b2.googledrive.com/host/0BzR3hjGfqNYFZlVWRUljMjhveEU/JPA.pdf

https://leanpub.com/high-performance-java-persistence