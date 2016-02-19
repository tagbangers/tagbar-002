# TagBar 2 (2016.2.19)

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

1. *HQL(Hibernate)*：HibernateのSQLライクなクエリ記述言語。HQLはSQLと違いテーブル名でなくクラス名、カラム名でなくプロパティ名を使う
2. *JPQL(JPA)*：JPAのSQLライクなクエリ記述言語。タイプセーフでない
3. *Criteria(JPA)*：HQLに比べよりオブジェクト思考で記述の少ない記法。条件の多い検索アプリなどに向いているが、可読性が低くメンテしづらい
4. *Querydsl*：クエリをタイプセーフかつ流れるようなインターフェースなDSLで記述できるライブラリ。エンティティのメタデータが生成されるため、コレを利用してタイプセーフを実現している
5. *Specification(Spring Data JPA)*：検索条件を表すインターフェースで、動的クエリを簡単に作成することができる。実装クラスではCriteriaAPIを使用する。

## 4. フェッチとカスケード

1. LazyInitializationException って？
    1. FetchType.EAGER
    2. javassist
    3. PersistentBag
2. JPQL による動的フェッチ
3. EntityGraph による動的フェッチ

## 5. 楽観的ロック (Optimistic)　と悲観的ロック (Pessimistic)



## 6. Multi-Tenancy

## 7. Hibernate ORM Envers

http://hibernate.org/orm/envers/

## 8. Hibernate Search

1. Hibernate Search による検索
    1. ユーザー辞書
    2. 類義語・同義語

## 9. 最近のリリースノート

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
		

## 参考

* [Hibernate ORM documentation](http://hibernate.org/orm/documentation/)
* [JPA with Spring](https://3f693e6410e6b3f1505c6d5f747d0a3fc10fd6b2.googledrive.com/host/0BzR3hjGfqNYFZlVWRUljMjhveEU/JPA.pdf)
* [High-Performance Java Persistence](https://leanpub.com/high-performance-java-persistence)

