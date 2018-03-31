package au.com.sentia.test.screen

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import au.com.sentia.test.model.Property
import au.com.sentia.test.screen.details.DetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class DetailActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<DetailActivity>(DetailActivity::class.java,
            true, false)


    val descriptionDummy = "Lorem ipsum dolor sit amet, pri eu oblique apeirian, cu mel homero aeterno. Ridens lobortis torquatos id vix. Ne homero vituperatoribus per. Vis rebum homero doming cu, an erat accusam hendrerit nec. Sale ignota suavitate eos id, est habeo alterum id, omnes soluta mediocrem per an.\n" +
            "\n" +
            "Eruditi invidunt voluptatibus cu vel, delectus percipit eos in, ut pri sumo sapientem. Vel cu wisi paulo, mea assum quando noster eu, cu nihil nullam intellegam eam. Ea omittam reprimique est. Te vide accumsan eam, vel in discere pericula.\n" +
            "\n" +
            "Duo cu dolore accumsan petentium. Suas omnis mei te, ad veniam vulputate quo. Ea pro utroque vocibus, tacimates referrentur comprehensam ad mea. Vim ad idque eruditi mentitum, mea id ornatus veritus disputando.\n" +
            "\n" +
            "Pro cu solum prompta, cum at tale urbanitas vituperatoribus, adhuc alienum insolens est an. Posse inani adipiscing mea no. Est an iudico ignota constituto, tempor tacimates atomorum ex quo, vim eripuit suscipit lobortis ad. Ex oporteat necessitatibus nec. Dolorum nominavi in duo. Cu ius option postulant iudicabit, ei quodsi torquatos eos, eum noluisse signiferumque no.\n" +
            "\n" +
            "Eos suas viderer ei, in pri voluptua legendos abhorreant, no decore consul nominavi est. Cum soluta nostrum suscipit in. In has esse minim. Ius unum possit signiferumque ea, cu modus nullam diceret est. Ius ne paulo libris voluptua."

    @Test
    fun dataPopulateTest() {

    }
}