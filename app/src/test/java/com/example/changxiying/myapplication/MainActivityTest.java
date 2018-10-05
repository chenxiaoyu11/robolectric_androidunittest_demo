package com.example.changxiying.myapplication;

import android.app.Application;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.changxiying.myapplication.activity.MainActivity;
import com.changxiying.myapplication.activity.NextActivity;
import com.changxiying.myapplication.view.TitleFragment;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.net.URISyntaxException;

@RunWith(RobolectricTestRunner.class)//表示用robolectric的TestRunner来跑这些case
public class MainActivityTest {
    @Before
    public void setUp() throws URISyntaxException {
//        输出日志,打印日志在控制台查看
        ShadowLog.stream = System.out;

    }

    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);//创建MainActivity的instance

//    @Before
//    public void prepareHttpResponse(String filePath) throws IOException {
//        String netDate = FileUtils.fileRead(FileUtils.toFile(getClass().getResource(filePath)), "utf8");
////        设置是否拦截真实的请求，默认为true：拦截。false：不拦截，真实的访问网络
//        FakeHttp.getFakeHttpLayer().interceptHttpRequests(true);
//        ProtocolVersion httpProtocolVersion = new ProtocolVersion("http", 1, 1);
//        HttpResponse httpResponse = new BasicHttpResponse(httpProtocolVersion, 400, "OK");
//        FakeHttp.setDefaultHttpResponse(httpResponse);
//        FakeHttp.addHttpResponseRule("http://www.baidu.com", httpResponse);
//        HttpGet httpGet = new HttpGet("http://www.baidu.com");
//        HttpResponse resultResponse = new DefaultHttpClient().execute(httpGet);
//        assertThat(resultResponse, is(httpResponse));
//    }

    //activity展示测试
    @Test
    public void testDisplayActivity() {
        Assert.assertNotNull(mainActivity);
        Assert.assertEquals("MyApplication", mainActivity.getTitle());
    }

    //activity跳转
    @Test
    public void testNextActivity() {
        //触发点击事件
        mainActivity.findViewById(R.id.button_next).performClick();
        /*
        ShodawRobolectric的本质是在Java运行环境下，采用Shadow的方式对Android中的组件进行模拟测试，从而实现Android单元测试。对于一些Robolectirc暂不支持的组件，可以采用自定义Shadow的方式扩展Robolectric的功能。
         */
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);//用来获取mainActivity对应的ShadowActivity的instance
        Intent nextStartedActivity = shadowActivity.getNextStartedActivity();//用来获取mainActivity调用的StartActivity的intent
        ShadowIntent shadowIntent = Shadows.shadowOf(nextStartedActivity);
        Assert.assertEquals(shadowIntent.getIntentClass(), NextActivity.class);
    }

    //UI组件联动测试
    @Test
    public void testViewState() {
        CheckBox checkBox = (CheckBox) mainActivity.findViewById(R.id.shanghai);
        EditText editText = (EditText) mainActivity.findViewById(R.id.editText1);
        checkBox.setChecked(true);
        Assert.assertEquals("上海选中", editText.getText().toString());
        checkBox.setChecked(false);
        Assert.assertEquals("上海取消选中", editText.getText().toString());

    }

    //Toast测试
    @Test
    public void testToastDiplay() {
        //点击按钮，出现吐司
        Button simpletoastBt = (Button) mainActivity.findViewById(R.id.button_simpletoast);
        simpletoastBt.performClick();
        Assert.assertEquals(ShadowToast.getTextOfLatestToast(), "简单的信息提示");
    }

    // 访问res
    @Test
    public void testAccessRes() {
        Application application = RuntimeEnvironment.application;
        String appName = application.getString(R.string.app_name);
        Assert.assertEquals("MyApplication", appName);
    }
//Fragment测试

    /**
     * 对于Fragment android有两个包：android.support.v4.app.Fragment和android.app.Fragment。Fragment（碎片）是在3.0以后才出现的，Google为了兼容3.0以前的版本，使用了android.support.v4来兼容以前的SDK。
     * android.app.Fragment 兼容的最低版本是android:minSdkVersion="11" 即3.0版
     * android.support.v4.app.Fragment 兼容的最低版本是android:minSdkVersion="4" 即1.6版
     * 对于Android开发来说，要用android.support.v4.app.Fragment 需要引入包android-support-v4.jar
     * 对于robolecttric单元测试，若测试support的fragment，需要导入包org.robolectric:shadows-support-v4:3.3.2 具体版本去https://mvnrepository.com/artifact/org.robolectric/shadows-support-v4查看
     * 选择自己需要的版本
     */
//    简单的例子
    @Test
    public void testFragment() {
        TitleFragment titleFragment = new TitleFragment();
//        添加fragment到activity，会触发Fragment的OnCreateView()
        SupportFragmentTestUtil.startFragment(titleFragment);
        Assert.assertNotNull(titleFragment.getView());
    }

}
