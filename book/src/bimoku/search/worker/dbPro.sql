
/*
--
-- 处理图书汇总出现的问题
-- 问题如下：
--    1.图书来自不同的站点会出现图书ISBN相同的编号，视为同一本书。
--    2.来自不同站点的图书可能出现书名不同（这是因为不同站点的编辑和购买活动的不同）。
--    3.在汇总的书库中不应该出现ISBN相同的图书。

-- 处理逻辑：
-- 1.插入图书的ISBN在总库中没有出现则添加到总库中。
-- 2.如果插入的图书的ISBN在总库中有，则首先判断是否是同一站点，如果是同一站点则不处理，如果不是同一站点则对图书的名称进行处理。添加到以前总库
-- 出现的图书中该站点的映射编号。
-- 3,。处理完成。

-- 图书名称的处理：每次插入到图书会查询各站点对应的编号查出相对应的图书的书名称，然后做一些处理，现在给出了站点（DD,DB,JD,AM,HD）

*/

Create Trigger beforeinsert BEFORE INSERT On t_test  FOR EACH ROW
as 
begin
if (SELECT  COUNT(*) FROM travelway)=0 then
TRUNCATE TABLE travelway
end if
end