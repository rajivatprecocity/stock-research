package com.rock.stock_research.test

import com.mchange.v2.c3p0.ComboPooledDataSource
import com.alibaba.fastjson.JSON
import scala.collection.mutable.ArrayBuffer
import com.alibaba.fastjson.JSONObject
import scala.beans.BeanProperty
import scalaj.http.Http
import org.jsoup.Jsoup
import scalaj.http.HttpOptions

object TestStock extends App {
  //  println("Hello")
  //
  //  val data = List(Map("id"->1223), Map("id"->456))
  //  println(data)
  //  val dd = data.sortBy((d:Map[String,Int])=>d("id"))
  //  println(data)
  //   println(dd)

  //   
  //   val str = """ {"id":123, "name":"john"}  """.trim()
  //		val json = JSON.parseObject(str)
  //		println(json.getInteger("id"))
  //		println(json.getString("name"))

  //  val arr = Seq(1,2.0,3)
  //  arr.filter((d:Double)=> d>1)
  //  println(arr.size)
  //  println(arr.length)
  //  

  //http://bdcjhq.hexun.com/quote?s2=000001.sh,399001.sz,399300.sz,601186.sh
  var str = """
    <html><head><meta http-equiv="Content-Type" content="text/html; charset=GBK"><script type='text/javascript'>document.domain='hexun.com';</script></head><body></body><script>try{parent.bdcallback({"000001.sh":{na:"上证指数",pc:"2485.606",op:"2477.966",vo:"220607619",tu:"19192499",hi:"2481.025",lo:"2457.045",la:"2478.824",type:"1",time:"2014-11-14 15:01:19"},"399001.sz":{na:"深证成指",pc:"8305.084",op:"8263.970",vo:"21578073",tu:"2100474",hi:"8343.914",lo:"8242.486",la:"8326.897",type:"1",time:"2014-11-14 15:00:46"},"399300.sz":{na:"沪深300 ",pc:"2579.750",op:"2569.211",vo:"165049965",tu:"14540993",hi:"2581.932",lo:"2557.666",la:"2581.093",type:"1",time:"2014-11-14 15:04:18"},"601186.sh":{na:"中国铁建",pc:"6.90",op:"6.82",vo:"806325",tu:"54723",hi:"6.88",lo:"6.72",la:"6.79",type:"2",time:"2014-11-14 15:00:20",sy:"7.73",lt:"102.61",sz:"837.72",hs:"0.79",is:"0"},"tofnow":{time:"2014-11-16 11:06:10"}})}catch(e){}</script></html>     
    """.trim

  var obj = JSON.parseObject("""
       {"600000.sh":{na:"�ַ�����",pc:"10.94",op:"10.88",vo:"1984143",tu:"215489",hi:"10.98",lo:"10.75",la:"10.90",type:"2",time:"2014-11-14 15:00:20",sy:"4.43",lt:"149.23",sz:"2033.23",hs:"1.33",is:"0"},"600004.sh":{na:"���ƻ���",pc:"9.80",op:"9.76",vo:"88040",tu:"8533",hi:"9.85",lo:"9.61",la:"9.68",type:"2",time:"2014-11-14 15:00:20",sy:"11.42",lt:"11.50",sz:"111.32",hs:"0.77",is:"0"},"600005.sh":{na:"��ֹɷ�",pc:"2.77",op:"2.74",vo:"1009441",tu:"27409",hi:"2.77",lo:"2.68",la:"2.71",type:"2",time:"2014-11-14 15:00:20",sy:"28.47",lt:"100.94",sz:"273.54",hs:"1.00",is:"0"},"600006.sh":{na:"��������",pc:"5.24",op:"5.22",vo:"175626",tu:"9188",hi:"5.32",lo:"5.16",la:"5.26",type:"2",time:"2014-11-14 15:00:26",sy:"49.72",lt:"20.00",sz:"105.20",hs:"0.88",is:"0"},"600007.sh":{na:"�й���ó",pc:"12.74",op:"12.76",vo:"46932",tu:"5852",hi:"12.76",lo:"12.31",la:"12.49",type:"2",time:"2014-11-14 15:00:26",sy:"29.79",lt:"10.07",sz:"125.81",hs:"0.47",is:"0"},"600008.sh":{na:"�״��ɷ�",pc:"7.20",op:"7.22",vo:"118919",tu:"8579",hi:"7.28",lo:"7.19",la:"7.20",type:"2",time:"2014-11-14 15:00:14",sy:"51.06",lt:"22.00",sz:"158.40",hs:"0.54",is:"0"},"600009.sh":{na:"�Ϻ�����",pc:"17.20",op:"17.15",vo:"214387",tu:"36300",hi:"17.18",lo:"16.70",la:"17.05",type:"2",time:"2014-11-14 15:00:20",sy:"16.52",lt:"10.93",sz:"328.55",hs:"1.96",is:"0"},"600010.sh":{na:"���ֹɷ�",pc:"3.08",op:"3.08",vo:"12165496",tu:"380488",hi:"3.25",lo:"3.04",la:"3.07",type:"2",time:"2014-11-14 15:00:20",sy:"352.87",lt:"157.42",sz:"491.36",hs:"7.73",is:"0"},"600011.sh":{na:"���ܹ���",pc:"6.56",op:"6.59",vo:"382114",tu:"25080",hi:"6.69",lo:"6.47",la:"6.61",type:"2",time:"2014-11-14 15:00:20",sy:"8.41",lt:"105.00",sz:"953.19",hs:"0.36",is:"0"},"600012.sh":{na:"��ͨ����",pc:"5.18",op:"5.17",vo:"80279",tu:"4199",hi:"5.32",lo:"5.12",la:"5.32",type:"2",time:"2014-11-14 15:00:20",sy:"10.12",lt:"11.66",sz:"88.24",hs:"0.69",is:"0"},"600015.sh":{na:"��������",pc:"9.02",op:"8.98",vo:"826844",tu:"73672",hi:"9.05",lo:"8.83",la:"8.93",type:"2",time:"2014-11-14 15:00:32",sy:"4.68",lt:"64.88",sz:"795.18",hs:"1.27",is:"0"},"600016.sh":{na:"��������",pc:"6.83",op:"6.78",vo:"2186644",tu:"147769",hi:"6.85",lo:"6.70",la:"6.77",type:"2",time:"2014-11-14 15:00:20",sy:"5.04",lt:"271.06",sz:"2304.48",hs:"0.81",is:"0"},"600017.sh":{na:"���ո�  ",pc:"4.03",op:"4.00",vo:"455763",tu:"17936",hi:"4.00",lo:"3.90",la:"3.94",type:"2",time:"2014-11-14 15:00:26",sy:"15.94",lt:"26.31",sz:"121.18",hs:"1.73",is:"0"},"600018.sh":{na:"�ϸۼ���",pc:"5.30",op:"0.00",vo:"0",tu:"0",hi:"0.00",lo:"0.00",la:"5.30",type:"2",time:"2014-11-14 15:00:26",sy:"20.20",lt:"227.55",sz:"1206.02",hs:"0.00",is:"3"},"600019.sh":{na:"���ֹɷ�",pc:"4.87",op:"4.83",vo:"947899",tu:"45793",hi:"4.94",lo:"4.76",la:"4.79",type:"2",time:"2014-11-14 15:00:20",sy:"12.75",lt:"164.24",sz:"789.00",hs:"0.58",is:"0"},"600020.sh":{na:"��ԭ����",pc:"3.20",op:"3.18",vo:"110095",tu:"3466",hi:"3.19",lo:"3.12",la:"3.16",type:"2",time:"2014-11-14 15:00:20",sy:"10.86",lt:"22.47",sz:"71.02",hs:"0.49",is:"0"},"600021.sh":{na:"�Ϻ�����",pc:"6.50",op:"6.57",vo:"556355",tu:"36178",hi:"6.73",lo:"6.27",la:"6.58",type:"2",time:"2014-11-14 15:00:20",sy:"15.77",lt:"21.40",sz:"140.79",hs:"2.60",is:"0"},"600022.sh":{na:"ɽ������",pc:"2.81",op:"2.98",vo:"4360399",tu:"127886",hi:"3.09",lo:"2.70",la:"2.73",type:"2",time:"2014-11-14 15:00:20",sy:"-18.93",lt:"53.43",sz:"175.71",hs:"8.16",is:"0"},"600023.sh":{na:"���ܵ���",pc:"5.99",op:"5.96",vo:"341087",tu:"19975",hi:"5.96",lo:"5.80",la:"5.86",type:"2",time:"2014-11-14 15:00:20"},"600026.sh":{na:"�к���չ",pc:"6.30",op:"6.32",vo:"182415",tu:"11216",hi:"6.37",lo:"6.06",la:"6.11",type:"2",time:"2014-11-14 15:00:20",sy:"-20.43",lt:"21.09",sz:"208.02",hs:"0.87",is:"0"},"600027.sh":{na:"�������",pc:"4.67",op:"4.67",vo:"745760",tu:"34482",hi:"4.74",lo:"4.52",la:"4.68",type:"2",time:"2014-11-14 15:00:20",sy:"7.54",lt:"58.80",sz:"412.18",hs:"1.27",is:"0"},"600028.sh":{na:"�й�ʯ��",pc:"5.31",op:"5.30",vo:"1113631",tu:"58311",hi:"5.30",lo:"5.21",la:"5.24",type:"2",time:"2014-11-14 15:00:26",sy:"9.17",lt:"912.82",sz:"6120.09",hs:"0.12",is:"0"},"600029.sh":{na:"�Ϸ�����",pc:"3.75",op:"3.88",vo:"1262034",tu:"47249",hi:"3.94",lo:"3.62",la:"3.64",type:"2",time:"2014-11-14 15:00:20",sy:"51.70",lt:"70.23",sz:"357.36",hs:"1.80",is:"0"},"600030.sh":{na:"����֤ȯ",pc:"15.06",op:"14.96",vo:"4423714",tu:"674092",hi:"15.55",lo:"14.80",la:"15.38",type:"2",time:"2014-11-14 15:00:20",sy:"21.41",lt:"98.15",sz:"1694.40",hs:"4.51",is:"0"},"600031.sh":{na:"��һ�ع�",pc:"6.26",op:"6.27",vo:"442621",tu:"27594",hi:"6.29",lo:"6.18",la:"6.26",type:"2",time:"2014-11-14 15:00:20",sy:"24.85",lt:"75.94",sz:"476.79",hs:"0.58",is:"0"},"600033.sh":{na:"��������",pc:"3.19",op:"3.15",vo:"381535",tu:"11869",hi:"3.15",lo:"3.08",la:"3.13",type:"2",time:"2014-11-14 15:00:20",sy:"13.14",lt:"27.44",sz:"85.90",hs:"1.39",is:"0"},"600035.sh":{na:"�������",pc:"3.79",op:"3.81",vo:"190980",tu:"7425",hi:"3.98",lo:"3.77",la:"3.92",type:"2",time:"2014-11-14 15:00:20",sy:"16.15",lt:"12.11",sz:"47.48",hs:"1.58",is:"0"},"600036.sh":{na:"��������",pc:"10.97",op:"10.93",vo:"885592",tu:"96246",hi:"10.98",lo:"10.78",la:"10.92",type:"2",time:"2014-11-14 15:00:26",sy:"4.74",lt:"206.29",sz:"2754.01",hs:"0.43",is:"0"},"600037.sh":{na:"�軪����",pc:"14.50",op:"14.48",vo:"135558",tu:"19643",hi:"14.74",lo:"14.24",la:"14.70",type:"2",time:"2014-11-14 15:00:20",sy:"36.31",lt:"10.64",sz:"156.38",hs:"1.27",is:"0"},"600038.sh":{na:"���ɹɷ�",pc:"35.49",op:"35.29",vo:"74453",tu:"26665",hi:"36.50",lo:"34.40",la:"36.25",type:"2",time:"2014-11-14 15:00:20",sy:"55.17",lt:"3.93",sz:"213.69",hs:"1.90",is:"0"},"600039.sh":{na:"�Ĵ�·��",pc:"4.63",op:"4.62",vo:"173811",tu:"8065",hi:"4.69",lo:"4.57",la:"4.65",type:"2",time:"2014-11-14 15:00:14",sy:"20.46",lt:"10.94",sz:"140.42",hs:"1.59",is:"0"},"600048.sh":{na:"�����ز�",pc:"6.11",op:"6.08",vo:"1461132",tu:"88725",hi:"6.15",lo:"5.99",la:"6.12",type:"2",time:"2014-11-14 15:00:20",sy:"5.37",lt:"107.27",sz:"656.47",hs:"1.36",is:"0"},"600050.sh":{na:"�й���ͨ",pc:"3.77",op:"3.75",vo:"1867951",tu:"69677",hi:"3.77",lo:"3.70",la:"3.75",type:"2",time:"2014-11-14 15:00:20",sy:"19.10",lt:"211.97",sz:"794.87",hs:"0.88",is:"0"},"600051.sh":{na:"��������",pc:"9.63",op:"9.50",vo:"32888",tu:"3133",hi:"9.62",lo:"9.43",la:"9.57",type:"2",time:"2014-11-14 15:00:20",sy:"19.56",lt:"3.02",sz:"29.75",hs:"1.09",is:"0"},"600052.sh":{na:"�㽭����",pc:"7.09",op:"7.01",vo:"78313",tu:"5503",hi:"7.14",lo:"6.96",la:"7.12",type:"2",time:"2014-11-14 15:00:26",sy:"12.49",lt:"8.72",sz:"62.07",hs:"0.90",is:"0"},"600053.sh":{na:"�н��ز�",pc:"10.14",op:"10.20",vo:"20128",tu:"2044",hi:"10.33",lo:"10.06",la:"10.31",type:"2",time:"2014-11-14 15:00:26",sy:"107.96",lt:"4.34",sz:"44.70",hs:"0.46",is:"0"},"600054.sh":{na:"��ɽ����",pc:"15.07",op:"15.01",vo:"36330",tu:"5386",hi:"15.01",lo:"14.68",la:"14.85",type:"2",time:"2014-11-14 15:00:26",sy:"34.17",lt:"1.18",sz:"70.00",hs:"3.09",is:"0"},"600055.sh":{na:"������",pc:"21.94",op:"21.70",vo:"21197",tu:"4574",hi:"22.03",lo:"20.80",la:"21.96",type:"2",time:"2014-11-14 15:00:20",sy:"121.59",lt:"2.16",sz:"47.53",hs:"0.98",is:"0"},"600056.sh":{na:"�й�ҽҩ",pc:"13.36",op:"13.35",vo:"75784",tu:"10083",hi:"13.43",lo:"13.21",la:"13.35",type:"2",time:"2014-11-14 15:00:20",sy:"23.29",lt:"8.84",sz:"135.17",hs:"0.86",is:"0"},"600057.sh":{na:"����ɷ�",pc:"11.06",op:"11.04",vo:"147260",tu:"16300",hi:"11.27",lo:"10.85",la:"11.15",type:"2",time:"2014-11-14 15:00:26",sy:"26.59",lt:"4.30",sz:"115.54",hs:"3.43",is:"0"},"600058.sh":{na:"���չ",pc:"14.91",op:"14.72",vo:"301531",tu:"43512",hi:"14.84",lo:"14.10",la:"14.27",type:"2",time:"2014-11-14 15:00:20",sy:"68.11",lt:"10.72",sz:"152.96",hs:"2.81",is:"0"},"600059.sh":{na:"��Խ��ɽ",pc:"8.54",op:"8.54",vo:"166759",tu:"14309",hi:"8.65",lo:"8.50",la:"8.56",type:"2",time:"2014-11-14 15:00:26",sy:"70.63",lt:"8.09",sz:"69.21",hs:"2.06",is:"0"},"600060.sh":{na:"���ŵ���",pc:"10.94",op:"10.91",vo:"126381",tu:"13850",hi:"11.07",lo:"10.85",la:"11.07",type:"2",time:"2014-11-14 15:00:26",sy:"11.72",lt:"13.08",sz:"144.85",hs:"0.97",is:"0"},"600061.sh":{na:"�з�Ͷ��",pc:"6.44",op:"0.00",vo:"0",tu:"0",hi:"0.00",lo:"0.00",la:"6.44",type:"2",time:"2014-11-14 15:00:20",sy:"-173.58",lt:"4.29",sz:"27.63",hs:"0.00",is:"3"},"600062.sh":{na:"����˫��",pc:"20.65",op:"20.57",vo:"72991",tu:"15414",hi:"21.30",lo:"20.57",la:"21.17",type:"2",time:"2014-11-14 15:00:14",sy:"15.59",lt:"5.72",sz:"121.03",hs:"1.28",is:"0"},"600063.sh":{na:"��ά����",pc:"4.03",op:"4.01",vo:"193383",tu:"7812",hi:"4.07",lo:"4.00",la:"4.05",type:"2",time:"2014-11-14 15:00:20",sy:"33.69",lt:"14.98",sz:"60.66",hs:"1.29",is:"0"},"600064.sh":{na:"�Ͼ��߿�",pc:"15.97",op:"15.80",vo:"67643",tu:"10480",hi:"15.87",lo:"15.29",la:"15.45",type:"2",time:"2014-11-14 15:00:20",sy:"18.23",lt:"5.16",sz:"79.76",hs:"1.31",is:"0"},"600066.sh":{na:"��ͨ�ͳ�",pc:"18.27",op:"18.20",vo:"150410",tu:"27728",hi:"18.57",lo:"18.12",la:"18.55",type:"2",time:"2014-11-14 15:00:20",sy:"11.51",lt:"12.45",sz:"235.65",hs:"1.21",is:"0"},"600067.sh":{na:"�ڳǴ�ͨ",pc:"6.88",op:"6.87",vo:"157604",tu:"10670",hi:"6.87",lo:"6.70",la:"6.79",type:"2",time:"2014-11-14 15:00:14",sy:"25.50",lt:"11.91",sz:"80.84",hs:"1.32",is:"0"},"600068.sh":{na:"���ް�  ",pc:"5.05",op:"5.04",vo:"416345",tu:"21090",hi:"5.12",lo:"5.02",la:"5.07",type:"2",time:"2014-11-14 15:00:26",sy:"11.39",lt:"34.87",sz:"233.46",hs:"1.19",is:"0"},"600069.sh":{na:"����Ͷ��",pc:"4.86",op:"4.86",vo:"49398",tu:"2376",hi:"4.87",lo:"4.78",la:"4.82",type:"2",time:"2014-11-14 15:00:20",sy:"-13.36",lt:"8.25",sz:"39.78",hs:"0.60",is:"0"},"600070.sh":{na:"�㽭����",pc:"9.49",op:"9.32",vo:"96197",tu:"9290",hi:"9.88",lo:"9.28",la:"9.71",type:"2",time:"2014-11-14 15:00:20",sy:"26.62",lt:"2.74",sz:"26.64",hs:"3.51",is:"0"},"600071.sh":{na:"��˹�ѧ",pc:"13.39",op:"13.35",vo:"21054",tu:"2842",hi:"13.77",lo:"13.26",la:"13.58",type:"2",time:"2014-11-14 15:00:20",sy:"-68.97",lt:"2.37",sz:"32.25",hs:"0.89",is:"0"},"600072.sh":{na:"*ST�ֹ� ",pc:"11.60",op:"11.61",vo:"46271",tu:"5468",hi:"11.95",lo:"11.60",la:"11.80",type:"2",time:"2014-11-14 15:00:20"},"600073.sh":{na:"�Ϻ�÷��",pc:"8.84",op:"8.80",vo:"158041",tu:"13972",hi:"8.96",lo:"8.73",la:"8.83",type:"2",time:"2014-11-14 15:00:26",sy:"43.60",lt:"7.76",sz:"72.65",hs:"2.04",is:"0"},"600074.sh":{na:"�д�ɷ�",pc:"6.54",op:"6.58",vo:"105769",tu:"6843",hi:"6.58",lo:"6.42",la:"6.43",type:"2",time:"2014-11-14 15:00:20"},"600075.sh":{na:"*ST��ҵ ",pc:"7.09",op:"7.06",vo:"26679",tu:"1863",hi:"7.07",lo:"6.92",la:"6.98",type:"2",time:"2014-11-14 15:00:26"},"600076.sh":{na:"���񻪹�",pc:"7.14",op:"7.08",vo:"39125",tu:"2766",hi:"7.18",lo:"7.02",la:"7.14",type:"2",time:"2014-11-14 15:00:26"},"600077.sh":{na:"�ζ��ɷ�",pc:"6.09",op:"6.10",vo:"221745",tu:"13493",hi:"6.21",lo:"5.91",la:"6.07",type:"2",time:"2014-11-14 15:00:20",sy:"18.60",lt:"10.91",sz:"66.22",hs:"2.03",is:"0"},"600078.sh":{na:"���ǹɷ�",pc:"9.28",op:"9.30",vo:"69073",tu:"6309",hi:"9.36",lo:"9.05",la:"9.05",type:"2",time:"2014-11-14 15:00:26",sy:"569.18",lt:"6.63",sz:"59.96",hs:"1.04",is:"0"},"600079.sh":{na:"�˸�ҽҩ",pc:"25.88",op:"25.98",vo:"60278",tu:"15526",hi:"26.07",lo:"25.38",la:"25.76",type:"2",time:"2014-11-14 15:00:20",sy:"31.31",lt:"5.06",sz:"136.21",hs:"1.19",is:"0"},"600080.sh":{na:"�𻨹ɷ�",pc:"11.25",op:"11.21",vo:"30903",tu:"3471",hi:"11.36",lo:"11.10",la:"11.21",type:"2",time:"2014-11-14 15:00:32",sy:"80.30",lt:"3.05",sz:"34.22",hs:"1.01",is:"0"},"600081.sh":{na:"����Ƽ�",pc:"13.81",op:"13.62",vo:"16177",tu:"2215",hi:"13.87",lo:"13.52",la:"13.63",type:"2",time:"2014-11-14 15:00:20",sy:"24.81",lt:"3.14",sz:"42.74",hs:"0.52",is:"0"},"600082.sh":{na:"��̩��չ",pc:"6.34",op:"6.25",vo:"126941",tu:"8018",hi:"6.42",lo:"6.20",la:"6.32",type:"2",time:"2014-11-14 15:00:20",sy:"83.38",lt:"6.30",sz:"40.83",hs:"2.01",is:"0"},"600083.sh":{na:"���Źɷ�",pc:"10.58",op:"10.55",vo:"23186",tu:"2438",hi:"10.63",lo:"10.43",la:"10.57",type:"2",time:"2014-11-14 15:00:26"},"600084.sh":{na:"���Ϲɷ�",pc:"5.56",op:"5.59",vo:"158420",tu:"8590",hi:"5.62",lo:"5.28",la:"5.31",type:"2",time:"2014-11-14 15:00:20"},"600085.sh":{na:"ͬ����  ",pc:"18.77",op:"18.73",vo:"311684",tu:"59893",hi:"19.46",lo:"18.62",la:"19.43",type:"2",time:"2014-11-14 15:00:32",sy:"34.99",lt:"13.11",sz:"254.76",hs:"2.38",is:"0"},"600086.sh":{na:"��������",pc:"24.11",op:"24.19",vo:"15085",tu:"3621",hi:"24.19",lo:"23.85",la:"24.08",type:"2",time:"2014-11-14 15:00:14",sy:"81.52",lt:"3.52",sz:"84.83",hs:"0.43",is:"0"},"600088.sh":{na:"���Ӵ�ý",pc:"19.61",op:"19.63",vo:"47138",tu:"9324",hi:"20.05",lo:"19.54",la:"19.65",type:"2",time:"2014-11-14 15:00:20",sy:"148.19",lt:"3.31",sz:"65.12",hs:"1.42",is:"0"},"600089.sh":{na:"�ر�繤",pc:"10.09",op:"10.01",vo:"576149",tu:"57207",hi:"10.02",lo:"9.86",la:"9.96",type:"2",time:"2014-11-14 15:00:20",sy:"19.18",lt:"31.66",sz:"322.72",hs:"1.82",is:"0"},"600090.sh":{na:"ơ�ƻ�  ",pc:"8.13",op:"0.00",vo:"0",tu:"0",hi:"0.00",lo:"0.00",la:"8.13",type:"2",time:"2014-11-14 15:00:26",sy:"25.76",lt:"3.68",sz:"29.91",hs:"0.00",is:"3"},"600091.sh":{na:"ST����  ",pc:"7.02",op:"0.00",vo:"0",tu:"0",hi:"0.00",lo:"0.00",la:"7.02",type:"2",time:"2014-11-14 15:00:26",sy:"-17.55",lt:"3.37",sz:"23.62",hs:"0.00",is:"3"},"600093.sh":{na:"�̼ιɷ�",pc:"10.35",op:"10.35",vo:"103778",tu:"10757",hi:"10.46",lo:"10.17",la:"10.27",type:"2",time:"2014-11-14 15:00:32",sy:"71.17",lt:"3.22",sz:"33.12",hs:"3.22",is:"0"},"600094.sh":{na:"������  ",pc:"7.78",op:"7.76",vo:"31696",tu:"2430",hi:"7.82",lo:"7.59",la:"7.67",type:"2",time:"2014-11-14 15:00:20",sy:"171.21",lt:"13.13",sz:"154.29",hs:"0.24",is:"0"},"600095.sh":{na:"���߿�  ",pc:"6.70",op:"6.70",vo:"42231",tu:"2813",hi:"6.74",lo:"6.60",la:"6.72",type:"2",time:"2014-11-14 15:00:26",sy:"35.42",lt:"3.61",sz:"24.28",hs:"1.17",is:"0"},"600096.sh":{na:"���컯  ",pc:"9.16",op:"9.19",vo:"98876",tu:"8976",hi:"9.20",lo:"8.99",la:"9.12",type:"2",time:"2014-11-14 15:00:20",sy:"-79.10",lt:"5.21",sz:"102.97",hs:"1.90",is:"0"},"600097.sh":{na:"��������",pc:"13.17",op:"13.17",vo:"9315",tu:"1225",hi:"13.26",lo:"13.05",la:"13.21",type:"2",time:"2014-11-14 15:00:26",sy:"24.25",lt:"1.15",sz:"26.76",hs:"0.81",is:"0"},"600098.sh":{na:"���ݷ�չ",pc:"6.38",op:"6.30",vo:"196791",tu:"12240",hi:"6.38",lo:"6.14",la:"6.24",type:"2",time:"2014-11-14 15:00:20"},"600099.sh":{na:"�ֺ��ɷ�",pc:"8.56",op:"8.53",vo:"18419",tu:"1574",hi:"8.62",lo:"8.46",la:"8.59",type:"2",time:"2014-11-14 15:00:14",sy:"998.84",lt:"2.19",sz:"18.82",hs:"0.84",is:"0"},"600100.sh":{na:"ͬ���ɷ�",pc:"10.98",op:"10.94",vo:"1034580",tu:"119367",hi:"12.00",lo:"10.87",la:"11.79",type:"2",time:"2014-11-14 15:00:32",sy:"39.01",lt:"20.70",sz:"259.13",hs:"5.00",is:"0"},"600101.sh":{na:"���ǵ���",pc:"8.87",op:"8.85",vo:"65774",tu:"5775",hi:"8.88",lo:"8.68",la:"8.77",type:"2",time:"2014-11-14 15:00:20",sy:"46.55",lt:"3.24",sz:"28.43",hs:"2.03",is:"0"},"600103.sh":{na:"��ɽֽҵ",pc:"3.66",op:"0.00",vo:"0",tu:"0",hi:"0.00",lo:"0.00",la:"3.66",type:"2",time:"2014-11-14 15:00:32",sy:"112.96",lt:"10.62",sz:"38.86",hs:"0.00",is:"3"},"600104.sh":{na:"��������",pc:"18.49",op:"18.49",vo:"274888",tu:"51175",hi:"18.90",lo:"18.40",la:"18.87",type:"2",time:"2014-11-14 15:00:20",sy:"7.64",lt:"92.42",sz:"2080.52",hs:"0.30",is:"0"},"600105.sh":{na:"�����ɷ�",pc:"9.86",op:"0.00",vo:"0",tu:"0",hi:"0.00",lo:"0.00",la:"9.86",type:"2",time:"2014-11-14 15:00:26",sy:"27.59",lt:"3.81",sz:"37.56",hs:"0.00",is:"3"},"600106.sh":{na:"����·��",pc:"4.67",op:"4.65",vo:"126619",tu:"5891",hi:"4.71",lo:"4.60",la:"4.67",type:"2",time:"2014-11-14 15:00:08",sy:"14.05",lt:"9.08",sz:"42.39",hs:"1.39",is:"0"},"600107.sh":{na:"������  ",pc:"7.60",op:"7.60",vo:"90490",tu:"6698",hi:"7.60",lo:"7.29",la:"7.42",type:"2",time:"2014-11-14 15:00:20",sy:"-191.73",lt:"3.60",sz:"26.71",hs:"2.51",is:"0"},"600108.sh":{na:"��ʢ����",pc:"8.69",op:"8.55",vo:"1431113",tu:"119363",hi:"8.71",lo:"8.18",la:"8.29",type:"2",time:"2014-11-14 15:00:26",sy:"55.45",lt:"19.47",sz:"161.40",hs:"7.35",is:"0"},"600109.sh":{na:"����֤ȯ",pc:"14.89",op:"14.69",vo:"1050221",tu:"152218",hi:"14.89",lo:"14.26",la:"14.39",type:"2",time:"2014-11-14 15:00:20",sy:"59.22",lt:"25.88",sz:"372.43",hs:"4.06",is:"0"},"600110.sh":{na:"�п�Ӣ��",pc:"6.94",op:"6.93",vo:"235114",tu:"16003",hi:"6.93",lo:"6.68",la:"6.75",type:"2",time:"2014-11-14 15:00:20",sy:"-145.16",lt:"11.50",sz:"77.65",hs:"2.04",is:"0"},"600111.sh":{na:"����ϡ��",pc:"22.76",op:"22.45",vo:"590166",tu:"131703",hi:"22.66",lo:"22.10",la:"22.34",type:"2",time:"2014-11-14 15:00:26",sy:"83.61",lt:"14.79",sz:"541.08",hs:"3.99",is:"0"},"600112.sh":{na:"��ɿع�",pc:"16.09",op:"0.00",vo:"0",tu:"0",hi:"0.00",lo:"0.00",la:"16.09",type:"2",time:"2014-11-14 15:00:20"},"600113.sh":{na:"�㽭����",pc:"8.32",op:"0.00",vo:"0",tu:"0",hi:"0.00",lo:"0.00",la:"8.32",type:"2",time:"2014-11-14 15:00:32",sy:"189.09",lt:"3.19",sz:"26.51",hs:"0.00",is:"3"},"600114.sh":{na:"�����ɷ�",pc:"14.51",op:"14.51",vo:"48795",tu:"6938",hi:"14.51",lo:"14.05",la:"14.14",type:"2",time:"2014-11-14 15:00:20",sy:"44.37",lt:"1.66",sz:"53.34",hs:"2.94",is:"0"},"600115.sh":{na:"��������",pc:"4.89",op:"4.95",vo:"1062870",tu:"50599",hi:"5.03",lo:"4.56",la:"4.65",type:"2",time:"2014-11-14 15:00:26",sy:"72.54",lt:"77.82",sz:"589.35",hs:"1.37",is:"0"},"600116.sh":{na:"��Ͽˮ��",pc:"12.18",op:"12.14",vo:"61989",tu:"7475",hi:"12.27",lo:"11.90",la:"12.15",type:"2",time:"2014-11-14 15:00:20",sy:"25.13",lt:"2.68",sz:"32.51",hs:"2.32",is:"0"},"600117.sh":{na:"�����ظ�",pc:"4.65",op:"4.64",vo:"61743",tu:"2824",hi:"4.65",lo:"4.52",la:"4.60",type:"2",time:"2014-11-14 15:00:32",sy:"-16.48",lt:"7.41",sz:"34.10",hs:"0.83",is:"0"},"600118.sh":{na:"�й�����",pc:"21.25",op:"21.10",vo:"178812",tu:"37776",hi:"21.55",lo:"20.66",la:"21.21",type:"2",time:"2014-11-14 15:00:20",sy:"83.14",lt:"11.82",sz:"250.81",hs:"1.51",is:"0"},"600119.sh":{na:"����Ͷ��",pc:"14.90",op:"14.80",vo:"55547",tu:"8365",hi:"15.32",lo:"14.70",la:"14.99",type:"2",time:"2014-11-14 15:00:32",sy:"139.31",lt:"3.07",sz:"46.08",hs:"1.81",is:"0"},"600120.sh":{na:"�㽭����",pc:"16.74",op:"16.66",vo:"160866",tu:"26715",hi:"16.90",lo:"16.40",la:"16.55",type:"2",time:"2014-11-14 15:00:20",sy:"14.97",lt:"5.05",sz:"83.66",hs:"3.18",is:"0"},"600121.sh":{na:"֣��ú��",pc:"5.31",op:"5.26",vo:"261519",tu:"14117",hi:"5.55",lo:"5.22",la:"5.39",type:"2",time:"2014-11-14 15:00:20",sy:"-30.98",lt:"6.29",sz:"54.73",hs:"4.16",is:"0"},"tofnow":{time:"2014-11-16 14:36:40"}}

        """.trim)
//  System.out.println(obj.getJSONObject("601186.sh").getString("pc"))
  
 var stockInfo:StockInfo =  obj.getObject("600000.sh", classOf[StockInfo])
 
 println(stockInfo.na )
 println(stockInfo.`type`  )
 
// val html = Http("http://bdcjhq.hexun.com/quote?s2=000001.sh,399001.sz,399300.sz,601186.sh").option(HttpOptions.readTimeout(111111)).option(HttpOptions.connTimeout(111111)).asString
// val doc = Jsoup.parse(html)
// var script = doc.select("script:eq(0)").html()
// println(script )
// script = script.replace("try{parent.bdcallback(", "").replace(")}catch(e){}", "")
// println(script)
}

class StockInfo {
  @BeanProperty var na: String = null
  @BeanProperty var pc: String = null
  @BeanProperty var op: String = null
  @BeanProperty var vo: String = null
  @BeanProperty var tu: String = null
  @BeanProperty var hi: String = null
  @BeanProperty var lo: String = null
  @BeanProperty var la: String = null
  @BeanProperty var `type`: String = null
  @BeanProperty var time: String = null
  @BeanProperty var sy: String = null
  @BeanProperty var lt: String = null
  @BeanProperty var sz: String = null
  @BeanProperty var hs: String = null
  @BeanProperty var is: String = null
}

